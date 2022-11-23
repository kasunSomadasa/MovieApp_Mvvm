package com.krs.movie.ui.tvshow.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.krs.movie.data.model.tvshow.TvShow
import com.krs.movie.repository.FakeTvRepository
import com.krs.movie.utils.Resource
import com.krs.newsapp.data.getOrAwaitValue
import org.junit.After
import org.junit.Before

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TvShowViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var tvShowViewModel: TvShowViewModel

    @Before
    fun setUp() {
        val fakeTvRepository = FakeTvRepository()
        tvShowViewModel = TvShowViewModel(fakeTvRepository)
    }

    @After
    fun tearDown() {
    }

    /**
     * checking if the function return correct list
     */
    @Test
    fun getPopularTvShow_returnCurrentList() {
        val tvList = mutableListOf<TvShow>()

        tvList.add(TvShow(1, "name1", "overview1", "/path/1", "2022/12/10"))
        tvList.add(TvShow(2, "name2", "overview2", "/path/2", "2022/12/12"))

        tvShowViewModel.getPopularTvShow()
        var response = tvShowViewModel.tvShowLiveData.getOrAwaitValue()
        when (response) {
            is Resource.Success -> {
                response.data?.let {
                    Truth.assertThat(it).isEqualTo(tvList)
                }
            }
        }
    }
}