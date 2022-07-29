package com.chrisabbod.githubrepolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.chrisabbod.githubrepolist.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvRepoList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val githubAPI = RetrofitHelper.getInstance().create(GithubAPI::class.java)

        val mainActivityJob = Job()

        val coroutineScope = CoroutineScope(mainActivityJob + Dispatchers.Main)
        coroutineScope.launch {
            val result = githubAPI.getRepositories().body()

            if (result != null) {
                binding.rvRepoList.adapter = RepoListAdapter(result)
            }
        }
    }
}