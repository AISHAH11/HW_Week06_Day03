package com.example.homeworksocial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), Callback<List<Post>> {

    val postAdapter=PostAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView=findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = postAdapter

        val gson = GsonBuilder()
            .setLenient()
            .create()
        val retrofit = Retrofit.Builder()
            .baseUrl(APIController.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        val gerritAPI = retrofit.create(SocialService::class.java)
        val call = gerritAPI.getAllPosts()
        call.enqueue(this)

        // load data
        //val list:List<Post> =
        //postAdapter.setData(list)
    }

    override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {

        if(response.isSuccessful){
            postAdapter.setData(response.body()!!)
        }else{
            Toast.makeText(this,response.message(),Toast.LENGTH_LONG).show()
        }
    }

    override fun onFailure(call: Call<List<Post>>, t: Throwable) {
        Toast.makeText(this,t.stackTraceToString(),Toast.LENGTH_LONG).show()
    }
}