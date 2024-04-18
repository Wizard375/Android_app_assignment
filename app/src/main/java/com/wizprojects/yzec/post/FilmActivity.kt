package com.wizprojects.yzec.post

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.wizprojects.yzec.HomeActivity
import com.wizprojects.yzec.Models.Film
import com.wizprojects.yzec.Models.User
import com.wizprojects.yzec.databinding.ActivityFilmBinding
import com.wizprojects.yzec.utils.FILM
import com.wizprojects.yzec.utils.FILM_FOLDER
import com.wizprojects.yzec.utils.USER_NODE
import com.wizprojects.yzec.utils.uploadVideo

class FilmActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityFilmBinding.inflate(layoutInflater)
    }

   private lateinit var videoUrl:String
   lateinit var progressDialog:ProgressDialog

    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()){
            uri->

        uri?.let {
            uploadVideo(uri, FILM_FOLDER,this@FilmActivity, progressDialog){
                    url->
                if (url != null) {
                    videoUrl=url
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        progressDialog=ProgressDialog(this)

        binding.addFilm.setOnClickListener {
            launcher.launch("video/*")
        }

        binding.cancel.setOnClickListener {
            startActivity(Intent(this@FilmActivity, HomeActivity::class.java))
            finish()
        }

        binding.newPostAdd.setOnClickListener {
            Firebase.firestore.collection(USER_NODE).document(Firebase.auth.currentUser!!.uid).get().addOnSuccessListener {
                var user:User = it.toObject<User>()!!

                val film: Film = Film(videoUrl!!,binding.caption.editText?.text.toString(),user.image!!)

                Firebase.firestore.collection(FILM).document().set(film).addOnSuccessListener {
                    Firebase.firestore.collection(Firebase.auth.currentUser!!.uid+ FILM).document().set(film).addOnSuccessListener {
                        startActivity(Intent(this@FilmActivity,HomeActivity::class.java))
                        finish()
                    }
                }
            }

        }
    }
}