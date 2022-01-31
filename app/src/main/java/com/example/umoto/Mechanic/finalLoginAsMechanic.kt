package com.example.umoto.Mechanic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.umoto.R
import kotlinx.android.synthetic.main.activity_final_login_as_mechanic.*

class finalLoginAsMechanic : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final_login_as_mechanic)

        finalloginAsMechanicSignupButton.setOnClickListener {
            startActivity(Intent(this,LoginAsAMechanic::class.java))
            finish()
        }
    }
}