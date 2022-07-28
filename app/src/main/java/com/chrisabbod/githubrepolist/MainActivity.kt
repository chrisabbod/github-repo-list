package com.chrisabbod.githubrepolist

import android.R
import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.chrisabbod.githubrepolist.data.Item
import com.chrisabbod.githubrepolist.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineExceptionHandler
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
            val result = githubAPI.getRepositories()
            val resultList: List<Item> = result.repo
            binding.rvRepoList.adapter = RepoListAdapter(resultList)
            Log.d("Chris", result.toString())
        }
    }
}