package com.wizprojects.yzec.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.wizprojects.yzec.Models.Post
import com.wizprojects.yzec.adapters.MyPostRvAdapter.*
import com.wizprojects.yzec.databinding.MyPostRvDesignBinding

class MyPostRvAdapter(var context: Context,var postList:ArrayList<Post>):RecyclerView.Adapter<MyPostRvAdapter.ViewHolder>() {
    inner class ViewHolder(var binding:MyPostRvDesignBinding):
            RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding=MyPostRvDesignBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.postImage
        Picasso.get().load(postList.get(position).postUrl).into(holder.binding.postImage)
    }
}