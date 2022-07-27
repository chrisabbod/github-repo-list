package com.chrisabbod.githubrepolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.chrisabbod.githubrepolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val items = listOf(
        "JetBrains/kotlin - The Kotlin Programming Language",
        "exercism/kotlin - Exercism exercises in Kotlin",
        "cbeust/kobalt - A Kotlin-based build system for the JVM",
        "JetBrains/kotlin - The Kotlin Programming Language",
        "exercism/kotlin - Exercism exercises in Kotlin",
        "cbeust/kobalt - A Kotlin-based build system for the JVM",
        "JetBrains/kotlin - The Kotlin Programming Language"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvRepoList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvRepoList.adapter = RepoListAdapter(items)

        val url =
            "https://api.github.com/search/repositories?q=mario+language:kotlin&sort=stars&order=desc"
    }
}