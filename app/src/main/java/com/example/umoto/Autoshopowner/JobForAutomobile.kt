package com.example.umoto.Autoshopowner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.umoto.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_job_for_automobile.*

class JobForAutomobile : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_for_automobile)

        mAuth = FirebaseAuth.getInstance()

        bye.setOnClickListener {
            Firebase.auth.signOut()
        }
    }
}