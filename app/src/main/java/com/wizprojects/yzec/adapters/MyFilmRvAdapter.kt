package com.wizprojects.yzec.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.wizprojects.yzec.Models.Film
import com.wizprojects.yzec.databinding.MyPostRvDesignBinding

class MyFilmRvAdapter(var context: Context, var filmList:ArrayList<Film>):
    RecyclerView.Adapter<MyFilmRvAdapter.ViewHolder>() {
    inner class ViewHolder(var binding: MyPostRvDesignBinding):
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding= MyPostRvDesignBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return filmList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context)
            .load(filmList.get(position).filmUrl)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.binding.postImage);
    }
}