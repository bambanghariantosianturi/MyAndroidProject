package com.example.myandroidproject.detail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.myandroidproject.core.commonconstant.CommonConstant
import com.example.myandroidproject.core.data.Resource
import com.example.myandroidproject.detail.databinding.ActivityDetailBinding
import com.example.myandroidproject.detail.di.DetailAppScope
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    companion object {
        fun startActivity(activity: Activity, bundle: Bundle) {
            activity.startActivity(Intent(activity, DetailActivity::class.java).apply {
                putExtras(
                    bundle
                )
            })
        }
    }

    @DetailAppScope
    private val detailViewModel: DetailViewModel by viewModels()

    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding
    private val mDefaultMovieId = 497698

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.hide()
//        Toast.makeText(this, "Success get detail movie data", Toast.LENGTH_SHORT).show()

//        detailViewModel.getDetailMovieData()

        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val a = intent.getIntExtra(CommonConstant.MOVIE_ID, 0) ?: 0
        var b: Int = 0
        if (a != 0) {
            b = a
        }

        observeData(binding, b)
    }

    private fun observeData(binding: ActivityDetailBinding?, movieId: Int) {
        detailViewModel.getDetailMovieData(movieId).observe(this, Observer {
            if (it.data != null) {
                when (it) {
                    is Resource.Loading -> {
                        ProgressBar.VISIBLE
                    }
                    is Resource.Success -> {
                        Toast.makeText(this, "Success get detail movie data", Toast.LENGTH_SHORT)
                            .show()
                        ProgressBar.GONE
                        val a = it.data
                        print(a)
                        if (binding != null) {
                            Glide.with(binding.ivDetailMovie)
                                .load(BuildConfig.IMG_URL + it.data?.backdrop_path)
                                .placeholder(android.R.drawable.btn_star)
                                .into(binding.ivDetailMovie)
                        }
                        binding?.tvTitleMovie?.text = it.data?.title
                        binding?.tvDescMovie?.text = it.data?.overview
                    }
                    is Resource.Error -> {
                        Toast.makeText(this, "Error get detail movie data", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        })
    }
}