package com.example.homeworksocial

import com.example.homeworksocial.Post
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.homeworksocial.SocialService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class APIController : Callback<List<Post>> {
    fun start() {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        val gerritAPI = retrofit.create(SocialService::class.java)
        val call = gerritAPI.getAllPosts()
        call.enqueue(this)
    }

    override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
        if (response.isSuccessful) {
            val changesList = response.body()
        } else {
            println(response.errorBody())
        }
    }

    override fun onFailure(call: Call<List<Post>>, t: Throwable) {
        t.printStackTrace()
    }

    companion object {
        const val BASE_URL = "https://61938d0dd3ae6d0017da866b.mockapi.io/api/"
    }
}