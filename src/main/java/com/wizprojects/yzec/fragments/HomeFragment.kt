package com.wizprojects.yzec.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.wizprojects.yzec.Models.Post
import com.wizprojects.yzec.Models.User
import com.wizprojects.yzec.R
import com.wizprojects.yzec.adapters.PostAdapter
import com.wizprojects.yzec.adapters.StoriesRvAdapter
import com.wizprojects.yzec.databinding.FragmentHomeBinding
import com.wizprojects.yzec.utils.FOLLOW
import com.wizprojects.yzec.utils.POST


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private var postList = ArrayList<Post>()
    private lateinit var adapter: PostAdapter
    private var storyList = ArrayList<User>()
    private lateinit var storyAdapter: StoriesRvAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        adapter = PostAdapter(requireContext(),postList)
        binding.postRv.layoutManager = LinearLayoutManager(requireContext())
        binding.postRv.adapter = adapter

        storyAdapter = StoriesRvAdapter(requireActivity(),storyList)
        binding.stories.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        binding.stories.adapter = storyAdapter
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.materialToolbar)
        setHasOptionsMenu(true)

        Firebase.firestore.collection(Firebase.auth.currentUser!!.uid+ FOLLOW).get().addOnSuccessListener {
            var tempList = ArrayList<User>()
            storyList.clear()
            for (i in it.documents){
                var user: User = i.toObject<User>()!!
                tempList.add(user)
            }
            storyList.addAll(tempList)
            storyAdapter.notifyDataSetChanged()
        }

        Firebase.firestore.collection(POST).get().addOnSuccessListener {
            var tempList = ArrayList<Post>()
            postList.clear()
            for (i in it.documents){
                var post:Post = i.toObject<Post>()!!
                tempList.add(post)
            }
            postList.addAll(tempList)
            adapter.notifyDataSetChanged()

        }


        return binding.root
    }

    companion object {

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.option_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}