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
    private val coroutineScope = CoroutineScope(Dispatchers.IO + parentJob)

    private val githubAPI: GithubAPI = RetrofitHelper.getInstance().create(GithubAPI::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvRepoList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        coroutineScope.launch(Dispatchers.Main) {
            val result = getRepoListAsync().await()

            if (result != null) {
                binding.rvRepoList.adapter = RepoListAdapter(result)
            }
        }
    }

    private fun getRepoListAsync(): Deferred<List<Data>?> =
        coroutineScope.async(Dispatchers.IO) {
            return@async githubAPI.getRepositories().body()
        }
}