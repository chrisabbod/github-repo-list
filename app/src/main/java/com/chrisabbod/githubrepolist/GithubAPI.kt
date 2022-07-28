package com.chrisabbod.githubrepolist

import com.chrisabbod.githubrepolist.data.RepoArray
import retrofit2.http.GET

interface GithubAPI {

    @GET("/repositories")
    suspend fun getRepositories() : RepoArray
}