package com.vickykdv.mymovie.ui.movie.popular

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.vickykdv.mymovie.data.model.movie.DataMovie
import com.vickykdv.mymovie.databinding.ActivityMoviePopularBinding
import com.vickykdv.mymovie.databinding.BottomSheetBinding
import com.vickykdv.mymovie.state.MovieState
import dagger.hilt.android.AndroidEntryPoint
import com.vickykdv.mymovie.ui.movie.adapter.AllMovieAdapter
import com.vickykdv.mymovie.ui.movie.detail.DetailMovieActivity

@AndroidEntryPoint
class PopularMovieActivity : AppCompatActivity() {

    private val binding : ActivityMoviePopularBinding by lazy {
        ActivityMoviePopularBinding.inflate(layoutInflater)
    }

    private val viewModel : PopularMovieViewModel by viewModels()

    private val adapter : AllMovieAdapter by lazy {
        AllMovieAdapter { item -> detailMovie(item)}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupStatusBar()
        setupView()
        getData()
        setupViewModel()
    }

    private fun setupView() {
        with(binding) {
            rvMovie.also {
                it.adapter = adapter
                it.layoutManager = LinearLayoutManager(
                    this@PopularMovieActivity, LinearLayoutManager.VERTICAL, false
                )
                it.setHasFixedSize(true)
            }

            imgBack.setOnClickListener { finish() }
        }
    }

    private fun getData() {
        viewModel.getMovie()
    }

    private fun setupViewModel() {
        viewModel.state.observe(this, {
            when (it) {
                is MovieState.Loading -> getLoadingUpcoming(true)
                is MovieState.Result -> getLoadingUpcoming(false)
                is MovieState.Error -> showError()
            }
        })
        viewModel.data.observe(this, Observer(adapter::submitList))
    }

    private fun getLoadingUpcoming(loading: Boolean) {
        with(binding) {
            if (loading) {
                rvMovie.visibility = View.INVISIBLE
                shMovie.visibility = View.VISIBLE
            }else {
                rvMovie.visibility = View.VISIBLE
                shMovie.visibility = View.INVISIBLE
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
        startActivity(Intent(this, DetailMovieActivity::class.java).also {
            it.putExtra("data", item)
        })
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
            navigationBarColor = ContextCompat.getColor(this@PopularMovieActivity, android.R.color.white)
            statusBarColor = ContextCompat.getColor(this@PopularMovieActivity, android.R.color.white)
        }
    }
}