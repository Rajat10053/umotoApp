package com.example.umoto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.umoto.User.LoginUpAsAUserActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginAsUserButton.setOnClickListener {
            startActivity(Intent(this, LoginUpAsAUserActivity::class.java))
            finish()
        }




    }
}