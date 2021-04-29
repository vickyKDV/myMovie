package com.vickykdv.mymovie.ui.movie.detail.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vickykdv.mymovie.BuildConfig.youtubeUrl
import com.vickykdv.mymovie.data.model.videos.DataVideo
import com.vickykdv.mymovie.databinding.ItemVideosBinding

class VideoAdapter (
    private val showDetail: (DataVideo) -> Unit
) : RecyclerView.Adapter<VideoAdapter.ViewHolder>() {

    private var data = ArrayList<DataVideo>()

    fun setData(movieList: List<DataVideo>?) {
        if (movieList == null) return
        data.clear()
        data.addAll(movieList)
        notifyDataSetChanged()
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.view) {
            tvTitle.text = data[position].name

            holder.itemView.also {
                Glide.with(it.context)
                    .load(youtubeUrl + data[position].key + "/maxresdefault.jpg")
                    .centerCrop()
                    .into(imgPoster)
            }

            root.setOnClickListener {
                showDetail(data[position])
            }
        }
    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
            ItemVideosBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    class ViewHolder(val view: ItemVideosBinding) : RecyclerView.ViewHolder(view.root)

}