package com.chrisabbod.githubrepolist

import com.chrisabbod.githubrepolist.data.Data
import retrofit2.Response
import retrofit2.http.GET

interface GithubAPI {

    @GET("/repositories")
    suspend fun getRepositories(): Response<List<Data>>
}