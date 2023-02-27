package com.example.myandroidproject.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myandroidproject.MyApplication
import com.example.myandroidproject.core.data.Resource
import com.example.myandroidproject.core.ui.UserAdapter
import com.example.myandroidproject.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

//    @Inject
//    lateinit var factory: MainViewModelFactory

    private val mainViewModel: MainViewModel by viewModels()
//    {
//        factory
//    }

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding
    
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
//        (application as MyApplication).appComponent.inject(this@MainActivity)
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        if (!isFinishing) {
            val userAdapter = UserAdapter()
            userAdapter.onItemClick = { selectData ->
                Toast.makeText(this, selectData.login, Toast.LENGTH_SHORT).show()
            }


//            val factory = MainViewModelFactory.getInstance(this)
//            mainViewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]

//        viewModel.setName("Bams")
//        viewModel.message.observe(this, Observer {
//            binding.tvWelcome.text = it.welcomeMessage
//        })

        mainViewModel.getAllData().observe(this, Observer {
                if (it != null) {
                    when (it) {
                        is Resource.Loading -> {
                            Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show()
                        }
                        is Resource.Success -> {
                            GlobalScope.launch(Dispatchers.Main) {
                                delay(4000)
                                userAdapter.setData(it.data)
                            }
                        }
                        is Resource.Error -> {
                            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            with(binding?.rvUser) {
                this?.layoutManager = LinearLayoutManager(this@MainActivity)
                this?.setHasFixedSize(true)
                this?.adapter = userAdapter
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}