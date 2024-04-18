package com.wizprojects.yzec.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.wizprojects.yzec.Models.Film
import com.wizprojects.yzec.R
import com.wizprojects.yzec.databinding.FilmDgBinding

class FilmAdapter(var context:Context,var filmList: ArrayList<Film>): RecyclerView.Adapter<FilmAdapter.ViewHolder>() {
    inner class ViewHolder(var binding : FilmDgBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       var binding = FilmDgBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return filmList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(filmList.get(position).profileLink).placeholder(R.drawable.default_profile).into(holder.binding.profileImage)
        holder.binding.caption.setText(filmList.get(position).caption)
        holder.binding.videoView.setVideoPath(filmList.get(position).filmUrl)
        holder.binding.videoView.setOnPreparedListener {
            holder.binding.progressBar.visibility = View.GONE
            holder.binding.videoView.start()
        }
    }
}