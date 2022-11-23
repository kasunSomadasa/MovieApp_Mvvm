package com.krs.movie.ui.tvshow

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
import com.krs.movie.databinding.FragmentTvShowBinding
import com.krs.movie.ui.tvshow.viewmodel.TvShowViewModel
import com.krs.movie.ui.tvshow.viewmodel.TvShowViewModelFactory
import com.krs.movie.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TvShowFragment : Fragment() {

    @Inject
    lateinit var factory: TvShowViewModelFactory
    @Inject
    lateinit var tvShowAdapter: TvShowAdapter
    private lateinit var tvShowViewModel: TvShowViewModel
    private lateinit var binding: FragmentTvShowBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTvShowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvShowViewModel = ViewModelProvider(this, factory).get(TvShowViewModel::class.java)
        binding.retryButton.setOnClickListener {
            displayTvShowList()
        }
        initRecycleView()
    }

    /**
     * initialize recycler view
     */
    private fun initRecycleView() {
        binding.tvShowRecyclerView.apply {
            adapter = tvShowAdapter
            layoutManager = GridLayoutManager(activity, 2)
        }
        tvShowAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("tvShow", it)
            }
            findNavController().navigate(R.id.action_tvShowFragment_to_tvShowInfoFragment, bundle)
        }
        displayTvShowList()
    }

    /**
     * get popular tv show list from api and display it in recycler view
     */
    private fun displayTvShowList() {
        tvShowViewModel.getPopularTvShow()
        tvShowViewModel.tvShowLiveData.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        tvShowAdapter.setTvShowList(it.tvShows.toList())
                        tvShowAdapter.notifyDataSetChanged()
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
        binding.tvShowProgressBar.visibility = View.VISIBLE
        binding.tvShowRecyclerView.visibility = View.GONE
        binding.errorLayout.visibility = View.GONE
    }

    private fun hideProgressBar() {
        binding.tvShowProgressBar.visibility = View.GONE
        binding.tvShowRecyclerView.visibility = View.VISIBLE
    }

}