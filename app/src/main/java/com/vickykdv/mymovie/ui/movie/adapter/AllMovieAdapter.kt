package com.vickykdv.mymovie.ui.movie.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.vickykdv.mymovie.BuildConfig.imageUrl
import com.vickykdv.mymovie.R
import com.vickykdv.mymovie.data.model.movie.DataMovie
import com.vickykdv.mymovie.databinding.ItemMovieBinding
import com.vickykdv.mymovie.utils.Constant.Genres
import com.vickykdv.mymovie.utils.Utils


class AllMovieAdapter (
    private val showDetail: (DataMovie) -> Unit
) : PagedListAdapter<DataMovie, AllMovieAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.view) {
            tvTitle.text = getItem(position)?.title

            tvRating.text = getItem(position)?.vote_average.toString()

            tvRelease.text = getItem(position)?.release_date?.let {
                Utils.dateFormat(
                    it,
                    "yyyy-mm-dd",
                    "yyyy"
                )
            }

            if(getItem(position)?.genreIds?.isNotEmpty() == true) {
                tvGenres.text = getItem(position)?.genreIds?.get(0)?.let { Genres.getValue(it) }
            } else {
                tvGenres.visibility = View.GONE
            }

            holder.itemView.also {
                Glide.with(it.context)
                    .load( imageUrl + getItem(position)?.poster_path)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imgPoster)

                tvPopularity.text = getItem(position)?.popularity.toString() +
                        it.context.getString(R.string.title_viewers)
            }

            root.setOnClickListener {
                getItem(position)?.let { it1 -> showDetail(it1) }
            }
        }
    }

    companion object{
        private val DIFF_CALLBACK = object: DiffUtil.ItemCallback<DataMovie>(){
            override fun areContentsTheSame(oldItem: DataMovie, newItem: DataMovie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areItemsTheSame(oldItem: DataMovie, newItem: DataMovie): Boolean {
                return oldItem == newItem
            }
        }
    }
    class ViewHolder(val view: ItemMovieBinding) : RecyclerView.ViewHolder(view.root)
}