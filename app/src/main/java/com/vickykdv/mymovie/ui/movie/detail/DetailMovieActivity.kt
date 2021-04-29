package com.vickykdv.mymovie.ui.movie.detail

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.vickykdv.mymovie.BuildConfig.imageUrl
import com.vickykdv.mymovie.R
import com.vickykdv.mymovie.data.mapper.MovieMapper
import com.vickykdv.mymovie.data.model.detailmovie.ResponseDetailMovie
import com.vickykdv.mymovie.data.model.movie.DataMovie
import com.vickykdv.mymovie.data.model.videos.DataVideo
import com.vickykdv.mymovie.databinding.ActivityDetailMovieBinding
import com.vickykdv.mymovie.databinding.BottomSheetBinding
import com.vickykdv.mymovie.state.DetailMovieState
import com.vickykdv.mymovie.state.VideoState
import dagger.hilt.android.AndroidEntryPoint
import com.vickykdv.mymovie.ui.movie.detail.adapter.ProductionCompanyAdapter
import com.vickykdv.mymovie.ui.movie.detail.adapter.VideoAdapter
import com.vickykdv.mymovie.utils.Constant.MOVIE
import com.vickykdv.mymovie.utils.Utils.dateFormat
import com.vickykdv.mymovie.utils.Utils.delay
import java.lang.Exception


@AndroidEntryPoint
class DetailMovieActivity : AppCompatActivity() {

    private val binding : ActivityDetailMovieBinding by lazy {
        ActivityDetailMovieBinding.inflate(layoutInflater)
    }

    private val viewModel: DetailMovieViewModel by viewModels()

    private val data : DataMovie? by lazy {
        intent.getParcelableExtra("data")
    }

    private val adapterVideo : VideoAdapter by lazy {
        VideoAdapter { item -> playVideo(item)}
    }

    private val adapterProduction : ProductionCompanyAdapter by lazy {
        ProductionCompanyAdapter()
    }

    private val dataLocal by lazy {
        MovieMapper.mapResponseToEntity(data!!)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        delay()
        setupStatusBar()
        setupView()
        setupViewModel()
        setupListener()
    }

    private fun setupViewModel() {
        viewModel.state.observe(this, {
            when (it) {
                is DetailMovieState.Loading -> getLoadingDetailMovie(true)
                is DetailMovieState.Result -> successGetDetailMovie(it.data)
                is DetailMovieState.Error -> showError()
            }
        })

        viewModel.stateVideo.observe(this, {
            when (it) {
                is VideoState.Loading -> getLoadingDetailMovie(true)
                is VideoState.Result -> successGetDataVideos(it.data.results)
                is VideoState.Error -> showError()
            }
        })


        viewModel.getDetailMovie(data!!.id)
        viewModel.getVideos(MOVIE, data!!.id)
    }

    private fun setupView() {
        with(binding) {
            rvVideo.also {
                it.adapter = adapterVideo
                it.layoutManager = LinearLayoutManager(
                    this@DetailMovieActivity,
                    LinearLayoutManager.HORIZONTAL, false
                )
                it.setHasFixedSize(true)
            }

            rvProduction.also {
                it.adapter = adapterProduction
                it.layoutManager = LinearLayoutManager(
                    this@DetailMovieActivity,
                    LinearLayoutManager.HORIZONTAL, false
                )
                it.setHasFixedSize(true)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun successGetDetailMovie(response: ResponseDetailMovie) {
        getLoadingDetailMovie(false)
        with(binding) {
            tvTitle.text = response.title

            if(response.release_date.isNotEmpty()) {
                tvRelease.text =
                    dateFormat(
                        response.release_date,
                        "yyyy-mm-dd",
                        "dd MMMM yyyy"
                    )
            }

            tvPopularity.text = response.popularity.toString() + getString(R.string.title_viewers)
            tvRating.text = response.vote_average.toString()
            tvDescription.text = response.overview

            Glide.with(this@DetailMovieActivity)
                    .load(imageUrl + response.poster_path)
                    .into(imgPoster)

            Glide.with(this@DetailMovieActivity)
                    .load(imageUrl + response.backdrop_path)
                    .into(imgBackground)


            if(response.genres.isNotEmpty()){
                tvGenres.text = response.genres[0].name
            } else {
                tvGenres.text = "-"
            }
            adapterProduction.setData(response.production_companies)
        }
    }

    private fun successGetDataVideos(response: List<DataVideo>) {
        getLoadingDetailMovie(false)
        adapterVideo.setData(response)
        try {
            binding.btnTrailer.setOnClickListener {
                val intent = Intent(
                    Intent.ACTION_VIEW, Uri.parse("vnd.youtube://${response[0].key}")
                )
                startActivity(intent)
            }
        } catch (e : Exception) {
            showError()
        }
    }

    private fun getLoadingDetailMovie(loading: Boolean) {
        with(binding) {
            if (loading) {
                pgLoading.visibility = View.VISIBLE
            } else {
                pgLoading.visibility = View.GONE
            }
        }
    }

    private fun showError() {
        val binding = BottomSheetBinding.inflate(layoutInflater)
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(binding.root)
        with(binding) {
            btnOk.setOnClickListener {
                dialog.dismiss()
            }
        }

        dialog.show()
    }

    private fun setupListener() {
        binding.imgBack.setOnClickListener {
            finish()
        }
    }

    private fun playVideo(item: DataVideo) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube://${item.key}"))
        startActivity(intent)
    }

    private fun setupStatusBar() {
//        with(window) {
//            setFlags(
//                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
//                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
//            )
//        }
        with(window){
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    or WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
            }
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            navigationBarColor = ContextCompat.getColor(this@DetailMovieActivity, android.R.color.white)
            statusBarColor = ContextCompat.getColor(this@DetailMovieActivity, android.R.color.white)
        }
    }
}