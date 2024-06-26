package com.wizprojects.yzec.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.marlonlom.utilities.timeago.TimeAgo
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.wizprojects.yzec.Models.Post
import com.wizprojects.yzec.Models.User
import com.wizprojects.yzec.R
import com.wizprojects.yzec.databinding.PostRvBinding
import com.wizprojects.yzec.utils.USER_NODE


class PostAdapter(var context: Context, var postList: ArrayList<Post>):RecyclerView.Adapter<PostAdapter.MyHolder>() {

    inner class MyHolder(var binding : PostRvBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        var binding = PostRvBinding.inflate(LayoutInflater.from(context),parent,false)
        return MyHolder(binding)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        try {
            Firebase.firestore.collection(USER_NODE).document(postList.get(position).name).get().addOnSuccessListener {
                var user = it.toObject<User>()
                Glide.with(context).load(user!!.image).placeholder(R.drawable.user).into(holder.binding.profileImage)
                holder.binding.name.text = user.name
            }
        }catch (e:Exception){

        }

        Glide.with(context).load(postList.get(position).postUrl).placeholder(R.drawable.post_loading).into(holder.binding.postImage)
        try {
            val text = TimeAgo.using(postList.get(position).time.toLong())
            holder.binding.time.text = text
        }
        catch (e:Exception) {
            holder.binding.time.text = "few days ago"
        }

        holder.binding.sharePost.setOnClickListener{
            var i = Intent(Intent.ACTION_SEND)
            i.type = "text/plain"
            i.putExtra(Intent.EXTRA_TEXT,postList.get(position).postUrl)
            context.startActivity(i)
        }

        holder.binding.caption.text = postList.get(position).caption
        holder.binding.likePost.setOnClickListener{
            holder.binding.likePost.setImageResource(R.drawable.liked)
        }
    }
}