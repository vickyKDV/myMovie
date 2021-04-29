package com.vickykdv.mymovie.ui.movie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vickykdv.mymovie.BuildConfig.imageUrl
import com.vickykdv.mymovie.data.model.movie.DataMovie
import com.vickykdv.mymovie.databinding.ItemUpcomingMovieBinding
import com.vickykdv.mymovie.utils.Utils.nomalizeRating

class HorizontalMovieAdapter (
    private val showDetail: (DataMovie) -> Unit
) : RecyclerView.Adapter<HorizontalMovieAdapter.ViewHolder>() {

    private var data = ArrayList<DataMovie>()

    fun setData(movieList: List<DataMovie>?) {
        if (movieList.isNullOrEmpty()) return
        data.clear()
        data.addAll(movieList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.view) {
            val newRating = nomalizeRating(data[position].vote_average!!.toFloat())
            tvTitle.text = data[position].title
            rating.rating = newRating
            holder.itemView.also {
                Glide.with(it.context)
                    .load( imageUrl + data[position].poster_path)
                    .into(imgPoster)
            }

            root.setOnClickListener {
                showDetail(data[position])
            }
        }
    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemUpcomingMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    class ViewHolder(val view: ItemUpcomingMovieBinding) : RecyclerView.ViewHolder(view.root)

}