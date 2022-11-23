package com.krs.movie.ui.tvshow

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.krs.movie.R
import com.krs.movie.databinding.FragmentMovieInfoBinding
import com.krs.movie.databinding.FragmentTvShowBinding
import com.krs.movie.databinding.FragmentTvShowInfoBinding
import com.krs.movie.ui.movie.MovieInfoFragmentArgs

class TvShowInfoFragment : Fragment() {
    private lateinit var binding: FragmentTvShowInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTvShowInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // get selected tv show object from nav args
        val args: TvShowInfoFragmentArgs by navArgs()
        val tvShow = args.tvShow
        tvShow?.let {
            binding.titleText.text = it.name
            binding.overview.text = it.overview
            val imageUrl = "https://image.tmdb.org/t/p/w500/" + it.posterPath
            Glide.with(binding.imageView.context)
                .load(imageUrl)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        binding.progressBar.visibility = View.VISIBLE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        binding.progressBar.visibility = View.GONE
                        return false
                    }
                })
                .into(binding.imageView)
        }
    }
}