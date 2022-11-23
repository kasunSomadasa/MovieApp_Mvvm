package com.krs.movie.data.network

import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieApiServiceTest {
    private lateinit var movieApiService : MovieApiService
    private lateinit var server : MockWebServer

    @Before
    fun setUp() {
        server = MockWebServer()
        movieApiService = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApiService::class.java)
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    private fun enqueueMockResponse(
        fileName: String
    ){
        val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setBody(source.readString(Charsets.UTF_8))
        server.enqueue(mockResponse)
    }

    /**
     * checking if the service function send the request to the server properly
     */
    @Test
    fun getPopularMovies_requestSent_receivedExpected(){
        runBlocking {
            enqueueMockResponse("movie_response.json")
            val responseBody = movieApiService.getPopularMovies().body()
            val request = server.takeRequest()
            Truth.assertThat(responseBody).isNotNull()
            Truth.assertThat(request.path).isEqualTo("/movie/popular?api_key=79b2d5fa68edd9eafd05646a1a2dc30d")
        }
    }

    /**
     * checking if our service function receives the response properly
     */
    @Test
    fun getPopularMovies_receivedResponse_correctPageItemSize(){
        runBlocking {
            enqueueMockResponse("movie_response.json")
            val responseBody = movieApiService.getPopularMovies().body()
            val movieList = responseBody!!.movies
            Truth.assertThat(movieList.size).isEqualTo(20)
        }
    }


    /**
     * checking the content of the received objects
     */
    @Test
    fun getPopularMovies_receivedResponse_correctContent(){
        runBlocking {
            enqueueMockResponse("movie_response.json")
            val responseBody = movieApiService.getPopularMovies().body()
            val movieList = responseBody!!.movies
            val movie = movieList[0]
            Truth.assertThat(movie.title).isEqualTo("Black Adam")
            Truth.assertThat(movie.releaseDate).isEqualTo("2022-10-19")
            Truth.assertThat(movie.posterPath).isEqualTo("/3zXceNTtyj5FLjwQXuPvLYK5YYL.jpg")
        }
    }

    /**
     * checking if the service function send the request to the server properly
     */
    @Test
    fun getPopularTVShows_requestSent_receivedExpected(){
        runBlocking {
            enqueueMockResponse("tv_response.json")
            val responseBody = movieApiService.getPopularTVShows().body()
            val request = server.takeRequest()
            Truth.assertThat(responseBody).isNotNull()
            Truth.assertThat(request.path).isEqualTo("/tv/popular?api_key=79b2d5fa68edd9eafd05646a1a2dc30d")
        }
    }

    /**
     * checking if our service function receives the response properly
     */
    @Test
    fun getPopularTVShows_receivedResponse_correctPageItemSize(){
        runBlocking {
            enqueueMockResponse("tv_response.json")
            val responseBody = movieApiService.getPopularTVShows().body()
            val tvShowList = responseBody!!.tvShows
            Truth.assertThat(tvShowList.size).isEqualTo(20)
        }
    }


    /**
     * checking the content of the received objects
     */
    @Test
    fun getPopularTVShows_receivedResponse_correctContent(){
        runBlocking {
            enqueueMockResponse("tv_response.json")
            val responseBody = movieApiService.getPopularTVShows().body()
            val tvShowList = responseBody!!.tvShows
            val tvShow = tvShowList[0]
            Truth.assertThat(tvShow.name).isEqualTo("The Walking Dead")
            Truth.assertThat(tvShow.firstAirDate).isEqualTo("2010-10-31")
            Truth.assertThat(tvShow.posterPath).isEqualTo("/xf9wuDcqlUPWABZNeDKPbZUjWx0.jpg")
        }
    }
}