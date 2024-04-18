package com.wizprojects.yzec.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.wizprojects.yzec.Models.Film
import com.wizprojects.yzec.adapters.FilmAdapter
import com.wizprojects.yzec.databinding.FragmentFilmBinding
import com.wizprojects.yzec.utils.FILM

class FilmFragment : Fragment() {
    private lateinit var binding: FragmentFilmBinding
    lateinit var adapter: FilmAdapter
    var filmList=ArrayList<Film>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment'
        binding = FragmentFilmBinding.inflate(inflater,container,false)
        adapter = FilmAdapter(requireContext(),filmList)
        binding.viewPager.adapter = adapter
        Firebase.firestore.collection(FILM).get().addOnSuccessListener {
            var tempList = ArrayList<Film>()
            filmList.clear()

            for (i in it.documents){
                var film = i.toObject<Film>()!!
                tempList.add(film)
            }
            filmList.addAll(tempList)
            filmList.reverse()
            adapter.notifyDataSetChanged()
        }

        return binding.root

    }

    companion object {

    }
}