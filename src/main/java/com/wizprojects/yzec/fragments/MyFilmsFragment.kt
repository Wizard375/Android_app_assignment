package com.wizprojects.yzec.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.wizprojects.yzec.Models.Film
import com.wizprojects.yzec.adapters.MyFilmRvAdapter
import com.wizprojects.yzec.databinding.FragmentMyFilmsBinding
import com.wizprojects.yzec.utils.FILM


class MyFilmsFragment : Fragment() {
    private lateinit var binding:FragmentMyFilmsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       binding = FragmentMyFilmsBinding.inflate(inflater,container,false)
        var filmList=ArrayList<Film>()
        var adapter= MyFilmRvAdapter(requireContext(), filmList)
        binding.rv.layoutManager= StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        binding.rv.adapter=adapter
        Firebase.firestore.collection(Firebase.auth.currentUser!!.uid+ FILM).get().addOnSuccessListener {
            var tempList= arrayListOf<Film>()
            for (i in it.documents){
                var film: Film =i.toObject<Film>()!!
                tempList.add(film)
            }
            filmList.addAll(tempList)
            adapter.notifyDataSetChanged()
        }
        return binding.root
    }

    companion object {

    }
}