package com.example.homeworksocial

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class PostAdapter : RecyclerView.Adapter<PostViewHolder>() {
    var postList: List<Post> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.onBind(postList[position])
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    fun setData(newData: List<Post>) {
        postList = newData
        notifyDataSetChanged()
    }
}

class PostViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun onBind(post: Post) {
        itemView.findViewById<TextView>(R.id.name).text = post.name
        itemView.findViewById<TextView>(R.id.likes).text = "${post.likes} likes"
        itemView.findViewById<TextView>(R.id.textBody).text = post.postBody
        val avatar = itemView.findViewById<ImageView>(R.id.avatar)
        Picasso.get().load(post.avatar).into(avatar);
    }

}