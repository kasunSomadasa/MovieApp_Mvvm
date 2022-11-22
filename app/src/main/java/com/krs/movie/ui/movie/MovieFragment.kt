package com.krs.movie.ui.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.krs.movie.R
import com.krs.movie.databinding.FragmentMovieBinding
import com.krs.movie.utils.Resource
import com.krs.movie.ui.movie.viewmodel.MovieViewModel
import com.krs.movie.ui.movie.viewmodel.MovieViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MovieFragment : Fragment() {
    @Inject
    lateinit var factory: MovieViewModelFactory

    @Inject
    lateinit var movieAdapter: MovieAdapter

    private lateinit var movieViewModel: MovieViewModel
    private lateinit var binding: FragmentMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieViewModel = ViewModelProvider(this, factory).get(MovieViewModel::class.java)
        binding.retryButton.setOnClickListener {
            displayMovieList()
        }
        initRecycleView()
    }

    /**
     * initialize recycler view
     */
    private fun initRecycleView() {
        binding.movieRecyclerView.apply {
            adapter = movieAdapter
            layoutManager = GridLayoutManager(activity, 2)
        }
        movieAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("movie", it)
            }
            findNavController().navigate(R.id.action_movieFragment_to_movieInfoFragment, bundle)
        }
        displayMovieList()
    }

    /**
     * get popular movie list from api and display it in recycler view
     */
    private fun displayMovieList() {
        movieViewModel.getPopularMovies()
        movieViewModel.movieLiveData.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        movieAdapter.setMovieList(it.movies.toList())
                        movieAdapter.notifyDataSetChanged()
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let {
                        binding.errorLayout.visibility = View.VISIBLE
                        binding.errorTextview.text = "${it}"
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }

            }

        })

    }

    private fun showProgressBar() {
        binding.movieProgressBar.visibility = View.VISIBLE
        binding.movieRecyclerView.visibility = View.GONE
        binding.errorLayout.visibility = View.GONE
    }

    private fun hideProgressBar() {
        binding.movieProgressBar.visibility = View.GONE
        binding.movieRecyclerView.visibility = View.VISIBLE
    }
}