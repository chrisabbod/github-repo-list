package com.chrisabbod.githubrepolist.data

data class RepoArray(val repo: List<Item>)

data class Item(
    val id: Int,
    val name: String,
    val private: Boolean,
    val description: String
)