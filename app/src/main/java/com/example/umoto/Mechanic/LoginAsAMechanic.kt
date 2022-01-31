package com.example.umoto.Mechanic

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.umoto.R
import kotlinx.android.synthetic.main.activity_login_as_amechanic.*

class LoginAsAMechanic : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_as_amechanic)

        loginAsMechanicLoginButton.setOnClickListener {
            startActivity(Intent(this,finalLoginAsMechanic::class.java))
            finish()
        }
    }
}


