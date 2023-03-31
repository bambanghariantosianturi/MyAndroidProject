package com.example.myandroidproject.list

import android.annotation.TargetApi
import android.content.Intent
import android.graphics.PorterDuff
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myandroidproject.core.commonconstant.CommonConstant
import com.example.myandroidproject.core.data.Resource
import com.example.myandroidproject.core.ui.UserAdapter
import com.example.myandroidproject.kit.crossModuleNavigateTo
import com.example.myandroidproject.kit.getCrossModuleNavigator
import com.example.myandroidproject.list.databinding.ActivityListMovieBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class ListMovieActivity : AppCompatActivity() {

    companion object {
        const val GENRE_MOVIE_ID = "GENRE_MOVIE_ID"
    }

    private val listViewModel: ListMovieViewModel by viewModels()
    private var _binding: ActivityListMovieBinding? = null
    private val binding get() = _binding
    private var genreMovieId: Int = 0

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_movie)
        supportActionBar?.hide()

        val bundle = intent.extras
        if (bundle != null) {
            this.genreMovieId = bundle.getInt(GENRE_MOVIE_ID) ?: 0
        }
//        setSupportActionBar(binding.root)
//        supportActionBar?.apply {
//            setDisplayHomeAsUpEnabled(true)
//            setDisplayShowTitleEnabled(false)
//        }

//        setStatusBarSolidColor(R.color.white, true)
//        setupToolbar(
//            binding.toolbarCart,
//            getString(R.string.epharmacy_my_cart),
//            R.color.greyish_brown,
//            R.color.white,
//            R.drawable.ic_arrow_back
//        )

        _binding = ActivityListMovieBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val userAdapter = UserAdapter()
        userAdapter.onItemClick = { selectData ->

            crossModuleNavigateTo(getCrossModuleNavigator().classDetailActivity(), bundleOf().apply {
                putInt(CommonConstant.MOVIE_ID, selectData.id)
            })
        }

        listViewModel.getMovieList(1, genreMovieId).observe(this, Observer {
            if (it != null) {
                when (it) {
                    is Resource.Loading -> {

                    }
                    is Resource.Success -> {
                        Toast.makeText(this, "Berhasil dapat list movie", Toast.LENGTH_LONG).show()

                        GlobalScope.launch(Dispatchers.Main) {
                            delay(1000)
                            userAdapter.setData(it.data)
                            val a = it
                            print(a)
                        }
                    }
                    is Resource.Error -> {
                        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

        with(binding?.rvListMovie) {
            this?.layoutManager = LinearLayoutManager(this@ListMovieActivity, androidx.recyclerview.widget.RecyclerView.VERTICAL, false)
            this?.setHasFixedSize(true)
            this?.adapter = userAdapter
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    fun setStatusBarSolidColor(@ColorRes color: Int, isLightStatusBar: Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window?.let {
                it.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                if (isLightStatusBar && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    it.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                }
                it.statusBarColor = ContextCompat.getColor(this, color)
            }
        }
    }

//    fun setupToolbar(
//        binding: ToolbarLayoutBinding, title: String,
//        @ColorRes textColor: Int, @ColorRes backgroundColor: Int,
//        @DrawableRes backIcon: Int
//    ) {
//        binding.run {
//            btnBack.run {
//                setImageResource(backIcon)
//                setOnClickListener {
//                    onBackPressed()
//                }
//                setColorFilter(
//                    ContextCompat.getColor(context, textColor),
//                    PorterDuff.Mode.SRC_IN
//                )
//            }
//            tvTitle.run {
//                text = title
//                setTextColor(ContextCompat.getColor(context, textColor))
//            }
//            appBarBase.setBackgroundColor(
//                ContextCompat.getColor(
//                    appBarBase.context,
//                    backgroundColor
//                )
//            )
//        }
//    }
}