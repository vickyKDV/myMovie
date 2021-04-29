package com.vickykdv.mymovie.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.vickykdv.mymovie.data.model.movie.DataMovie
import com.vickykdv.mymovie.databinding.ActivityMovieBinding
import com.vickykdv.mymovie.databinding.BottomSheetBinding
import com.vickykdv.mymovie.state.MovieState
import com.vickykdv.mymovie.ui.movie.MovieViewModel
import com.vickykdv.mymovie.ui.movie.adapter.HorizontalMovieAdapter
import com.vickykdv.mymovie.ui.movie.adapter.VerticalMovieAdapter
import com.vickykdv.mymovie.ui.movie.detail.DetailMovieActivity
import com.vickykdv.mymovie.ui.movie.popular.PopularMovieActivity
import com.vickykdv.mymovie.ui.movie.toprated.TopRatedMovieActivity
import com.vickykdv.mymovie.ui.movie.upcoming.UpcomingMovieActivity
import com.vickykdv.mymovie.ui.search.movie.SearchMovieActivity
import com.vickykdv.mymovie.utils.Utils
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class MovieActivity : AppCompatActivity() {

    private val viewModel: MovieViewModel by viewModels()

    private val binding : ActivityMovieBinding by lazy {
        ActivityMovieBinding.inflate(layoutInflater)
    }

    private val upcomingAdapter: HorizontalMovieAdapter by lazy {
        HorizontalMovieAdapter{ item -> detailMovie(item)}
    }

    private val topRatedAdapter: HorizontalMovieAdapter by lazy {
        HorizontalMovieAdapter{ item -> detailMovie(item)}
    }

    private val popularAdapter: VerticalMovieAdapter by lazy {
        VerticalMovieAdapter{ item -> detailMovie(item)}
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupStatusBar()
        Utils.delay()
        setupView()
        setupViewModel()
        setupData()
        setupListener()
    }

   private fun setupData() {
        viewModel.getUpcomingMovie()
        viewModel.getPopularMovie()
        viewModel.getTopRatedMovie()
    }

    private fun setupViewModel() {
        viewModel.stateUpcoming.observe(this, {
            when(it){
                is MovieState.Loading   -> getLoadingUpcoming(true)
                is MovieState.Result    -> successGetDataUpComing(it.data.data)
                is MovieState.Error     -> showError()
            }
        })

        viewModel.stateTopRated.observe(this, {
            when(it){
                is MovieState.Loading   -> getLoadingPopular(true)
                is MovieState.Result    -> successGetDataTopRated(it.data.data)
                is MovieState.Error     -> showError()
            }
        })

        viewModel.statePopular.observe(this, {
            when(it){
                is MovieState.Loading   -> getLoadingPopular(true)
                is MovieState.Result    -> successGetDataPopular(it.data.data)
                is MovieState.Error     -> showError()
            }
        })
    }

    private fun setupView() {
        with(binding) {
            rvUpcoming.also {
                it.adapter = upcomingAdapter
                it.layoutManager = LinearLayoutManager(
                    this@MovieActivity, LinearLayoutManager.HORIZONTAL ,false)
                it.setHasFixedSize(true)
            }

            rvTopRated.also {
                it.adapter = topRatedAdapter
                it.layoutManager = LinearLayoutManager(
                    this@MovieActivity, LinearLayoutManager.HORIZONTAL ,false)
                it.setHasFixedSize(true)
            }

            rvPopular.also {
                it.adapter = popularAdapter
                it.layoutManager = GridLayoutManager(this@MovieActivity, 1)
                it.setHasFixedSize(true)
            }
        }
    }

    private fun successGetDataUpComing(data : List<DataMovie>) {
        getLoadingUpcoming(false)
        upcomingAdapter.setData(data)
    }

    private fun successGetDataTopRated(data : List<DataMovie>) {
        getLoadingTopRated(false)
        topRatedAdapter.setData(data)
    }

    private fun successGetDataPopular(data : List<DataMovie>) {
        getLoadingPopular(false)
        popularAdapter.setData(data)
    }

    private fun getLoadingUpcoming(loading: Boolean) {
        with(binding) {
            if (loading) {
                rvUpcoming.visibility = View.INVISIBLE
                shUpcoming.visibility = View.VISIBLE
            }else {
                rvUpcoming.visibility = View.VISIBLE
                shUpcoming.visibility = View.INVISIBLE
            }
        }
    }

    private fun getLoadingTopRated(loading: Boolean) {
        with(binding) {
            if (loading) {
                rvTopRated.visibility = View.INVISIBLE
                shTopRated.visibility = View.VISIBLE
            }else {
                rvTopRated.visibility = View.VISIBLE
                shTopRated.visibility = View.INVISIBLE
            }
        }
    }

    private fun getLoadingPopular(loading: Boolean) {
        with(binding) {
            if (loading) {
                rvPopular.visibility = View.INVISIBLE
                shPopular.visibility = View.VISIBLE
            }else {
                rvPopular.visibility = View.VISIBLE
                shPopular.visibility = View.INVISIBLE
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

    private fun detailMovie(item: DataMovie) {
        startActivity(Intent(this@MovieActivity, DetailMovieActivity::class.java).also {
            it.putExtra("data", item)
        })
    }

    private fun setupListener() {
        with(binding) {
            tvSeeUpcoming.setOnClickListener {
                startActivity(Intent(this@MovieActivity, UpcomingMovieActivity::class.java))
            }

            tvSeeTopRated.setOnClickListener {
                startActivity(Intent(this@MovieActivity, TopRatedMovieActivity::class.java))
            }

            tvSeePopular.setOnClickListener {
                startActivity(Intent(this@MovieActivity, PopularMovieActivity::class.java))
            }

            search.setOnClickListener {
                startActivity(Intent(this@MovieActivity, SearchMovieActivity::class.java))
            }

        }
    }

    private fun setupStatusBar() {
        with(window){
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    or WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
            }
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            navigationBarColor = ContextCompat.getColor(this@MovieActivity, android.R.color.white)
            statusBarColor = ContextCompat.getColor(this@MovieActivity, android.R.color.white)
        }
    }
}