package com.example.homeworksocial

import retrofit2.Call
import retrofit2.http.GET

interface SocialService {
    @GET("posts")
    fun getAllPosts():Call<List<Post>>
}