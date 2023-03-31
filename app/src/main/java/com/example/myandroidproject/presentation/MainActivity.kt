package com.example.myandroidproject.presentation

import android.content.Intent
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
//import com.example.chat.ChatActivity
//import com.example.chat.ChatActivity
import com.example.myandroidproject.core.data.Resource
import com.example.myandroidproject.core.ui.UserAdapter
import com.example.myandroidproject.databinding.ActivityMainBinding
import com.example.myandroidproject.detail.DetailActivity
import com.example.myandroidproject.kit.crossModuleNavigateTo
import com.example.myandroidproject.kit.getCrossModuleNavigator
import com.example.myandroidproject.list.ListMovieActivity
import com.example.myandroidproject.presentation.adapter.GenreAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

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
        supportActionBar?.hide()

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        if (!isFinishing) {
            val userAdapter = GenreAdapter()
            userAdapter.onItemClick = { selectData ->
                val bundle = Bundle()
                bundle.putInt(ListMovieActivity.GENRE_MOVIE_ID, selectData.id)
                val intent = Intent(this@MainActivity, ListMovieActivity::class.java)
                intent.putExtras(bundle)
                startActivity(intent)
            }

//            val factory = MainViewModelFactory.getInstance(this)
//            mainViewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]

//        viewModel.setName("Bams")
//        viewModel.message.observe(this, Observer {
//            binding.tvWelcome.text = it.welcomeMessage
//        })

            mainViewModel.getGenreMovie().observe(this, Observer {
                if (it != null) {
                    when (it) {
                        is Resource.Loading -> {

                        }
                        is Resource.Success -> {
//                            Toast.makeText(this, "Berhasil Dapet Genre Loh", Toast.LENGTH_SHORT).show()

                            GlobalScope.launch(Dispatchers.Main) {
                                delay(1000)
                                userAdapter.setDataGenre(it.data)
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

//        mainViewModel.getAllData().observe(this, Observer {
//                if (it != null) {
//                    when (it) {
//                        is Resource.Loading -> {
//                            Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show()
//                        }
//                        is Resource.Success -> {
//                            Toast.makeText(this, "Berhasil", Toast.LENGTH_SHORT).show()
//                            GlobalScope.launch(Dispatchers.Main) {
//                                delay(1000)
//                                userAdapter.setData(it.data)
//                                val a = it
//                                print(a)
//                            }
//                        }
//                        is Resource.Error -> {
//                            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
//                        }
//                    }
//                }
//            })

            with(binding?.rvUser) {
                this?.layoutManager = LinearLayoutManager(this@MainActivity, VERTICAL, false)
                this?.setHasFixedSize(true)
                this?.adapter = userAdapter
            }
        }

        binding?.fab?.setOnClickListener {
            try {
                moveToChatActivity()
//                installChatModule()
            } catch (e: Exception) {
                Toast.makeText(this, "Module Not Found", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun moveToChatActivity() {
        startActivity(Intent(this@MainActivity, Class.forName("com.example.myandroidproject.test.TestActivity")))
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
    }

//    private fun installChatModule() {
//        val splitInstallManager = SplitInstallManagerFactory.create(this)
//        val moduleChat = "chattwo"
//        if (splitInstallManager.installedModules.contains(moduleChat)) {
//            moveToChatActivity()
//            Toast.makeText(this, "Open module", Toast.LENGTH_SHORT).show()
//        } else {
//            val request = SplitInstallRequest.newBuilder()
//                .addModule(moduleChat)
//                .build()
//
//            splitInstallManager.startInstall(request)
//                .addOnSuccessListener {
//                    Toast.makeText(this, "Success installing module", Toast.LENGTH_SHORT).show()
//                    moveToChatActivity()
//                }
//                .addOnFailureListener {
//                    Toast.makeText(this, "Error installing module", Toast.LENGTH_SHORT).show()
//                }
//        }
//    }
}