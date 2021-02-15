package com.example.myapplication.network

import androidx.compose.runtime.Composable
import com.example.myapplication.ui.model.Feed
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface GitHubService{
    @GET("elephants")
    fun listRepos(): Call<List<Feed>>
}
class Network {
    companion object{
        var retrofit = Retrofit.Builder()
            .baseUrl("https://elephant-api.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var service: GitHubService = retrofit.create(GitHubService::class.java)

    }
}