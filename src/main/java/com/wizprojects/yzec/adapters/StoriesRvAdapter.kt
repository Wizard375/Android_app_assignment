package com.wizprojects.yzec.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wizprojects.yzec.Models.User
import com.wizprojects.yzec.R
import com.wizprojects.yzec.databinding.StoriesRvBinding

class StoriesRvAdapter(var context: Context,var storyList: ArrayList<User>): RecyclerView.Adapter<StoriesRvAdapter.ViewHolder>() {
    inner class ViewHolder(var binding: StoriesRvBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding = StoriesRvBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return storyList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(storyList.get(position).image).placeholder(R.drawable.default_profile).into(holder.binding.storyProfile)
        holder.binding.name.text = storyList.get(position).name
    }
}