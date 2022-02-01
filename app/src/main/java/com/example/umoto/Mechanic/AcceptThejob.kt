package com.example.umoto.Mechanic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.umoto.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_accept_thejob.*

class AcceptThejob : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accept_thejob)

        mAuth = FirebaseAuth.getInstance()

        signout.setOnClickListener {
            Firebase.auth.signOut()
        }
    }
}