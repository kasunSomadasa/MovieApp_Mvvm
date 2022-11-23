package com.krs.movie.ui.tvshow

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.krs.movie.data.model.tvshow.TvShow
import com.krs.movie.databinding.TvShowListItemBinding

/**
 * adapter class for movie list
 */
class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.TvShowHolder>() {

    private val tvShowList = ArrayList<TvShow>()

    fun setTvShowList(tvList : List<TvShow>) {
        tvShowList.clear()
        tvShowList.addAll(tvList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowAdapter.TvShowHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = TvShowListItemBinding.inflate(layoutInflater, parent, false)
        return TvShowHolder(binding)
    }

    override fun onBindViewHolder(holder: TvShowAdapter.TvShowHolder, position: Int) {
        holder.bind(tvShowList[position])
    }

    override fun getItemCount(): Int {
        return tvShowList.size
    }

    inner class TvShowHolder constructor(private val binding: TvShowListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvShow) {
            binding.titleText.text = tvShow.name
            binding.releaseDate.text = tvShow.firstAirDate
            val imageUrl = "https://image.tmdb.org/t/p/w500/" + tvShow.posterPath
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


            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    it(tvShow)
                }
            }
        }

    }

    private var onItemClickListener: ((TvShow) -> Unit)? = null
    fun setOnItemClickListener(listener: (TvShow) -> Unit) {
        onItemClickListener = listener
    }
}

