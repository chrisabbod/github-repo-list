package com.chrisabbod.githubrepolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.chrisabbod.githubrepolist.data.Data
import com.chrisabbod.githubrepolist.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val parentJob = Job()
    private val coroutineExceptionHandler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
            coroutineScope.launch(Dispatchers.Main) {
                Log.e("Coroutine Exception", "$throwable")
            }
        }
    private val coroutineScope = CoroutineScope(
        Dispatchers.IO +
                parentJob +
                coroutineExceptionHandler
    )

    private val githubAPI: GithubAPI = RetrofitHelper.getInstance().create(GithubAPI::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvRepoList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        coroutineScope.launch(Dispatchers.Main) {
            val result = getRepoListAsync()

            if (result != null) {
                binding.rvRepoList.adapter = RepoListAdapter(result)
            }
        }
    }

    private suspend fun getRepoListAsync(): List<Data>? =
        withContext(Dispatchers.IO) {
            return@withContext githubAPI.getRepositories().body()
        }
}