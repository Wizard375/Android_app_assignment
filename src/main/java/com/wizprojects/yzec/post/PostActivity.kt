package com.wizprojects.yzec.post

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.wizprojects.yzec.HomeActivity
import com.wizprojects.yzec.Models.Post
import com.wizprojects.yzec.Models.User
import com.wizprojects.yzec.databinding.ActivityPostBinding
import com.wizprojects.yzec.utils.POST
import com.wizprojects.yzec.utils.POST_FOLDER
import com.wizprojects.yzec.utils.USER_NODE
import com.wizprojects.yzec.utils.USER_PROFILE_FOLDER
import com.wizprojects.yzec.utils.uploadImage

class PostActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityPostBinding.inflate(layoutInflater)
    }

    var imageUrl:String?=null

    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()){
            uri->

        uri?.let {
            uploadImage(uri, POST_FOLDER){
                url->
                if (url != null) {
                    binding.selectImage.setImageURI(uri)
                    imageUrl=url
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.materialToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        binding.materialToolbar.setNavigationOnClickListener {
            startActivity(Intent(this@PostActivity,HomeActivity::class.java))
            finish()
        }

        binding.selectImage.setOnClickListener {
            launcher.launch("image/*")
        }

        binding.cancel.setOnClickListener {
            startActivity(Intent(this@PostActivity,HomeActivity::class.java))
            finish()
        }

        binding.newPostAdd.setOnClickListener {
            Firebase.firestore.collection(USER_NODE).document(Firebase.auth.currentUser!!.uid).get().addOnSuccessListener {
                var user = it.toObject<User>()

                val post:Post=Post(
                    postUrl = imageUrl!!,
                    caption = binding.caption.editText?.text.toString(),
                    name = Firebase.auth.currentUser!!.uid,
                    time = System.currentTimeMillis().toString())

                Firebase.firestore.collection(POST).document().set(post).addOnSuccessListener {
                    Firebase.firestore.collection(Firebase.auth.currentUser!!.uid).document().set(post).addOnSuccessListener {
                        startActivity(Intent(this@PostActivity,HomeActivity::class.java))
                        finish()
                    }
                }
            }

        }
    }
}