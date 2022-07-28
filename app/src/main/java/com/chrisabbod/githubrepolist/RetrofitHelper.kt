package com.chrisabbod.githubrepolist

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    private const val  baseUrl = "https://api.github.com/"

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
                //we need to add converter factory to convert JSON object to JAVA object
            .build()
    }
}