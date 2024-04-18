package com.wizprojects.yzec.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.wizprojects.yzec.R
import com.wizprojects.yzec.databinding.FragmentAddBinding
import com.wizprojects.yzec.post.FilmActivity
import com.wizprojects.yzec.post.PostActivity

class AddFragment : BottomSheetDialogFragment() {
    private lateinit var binding:FragmentAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding =  FragmentAddBinding.inflate(inflater, container, false)

        binding.post.setOnClickListener {
            activity?.startActivity(Intent(requireContext(),PostActivity::class.java))
            activity?.finish()
        }

        binding.addFilm.setOnClickListener {
            activity?.startActivity(Intent(requireContext(),FilmActivity::class.java))
        }

    return binding.root
    }

    companion object {

    }
}