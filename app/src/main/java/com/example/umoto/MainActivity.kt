package com.example.umoto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.umoto.Autoshopowner.LoginAsAAutoshopOwner
import com.example.umoto.Mechanic.LoginAsAMechanic
import com.example.umoto.User.signUpAsAUserActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginAsUserButton.setOnClickListener {
            startActivity(Intent(this, signUpAsAUserActivity::class.java))
            finish()
        }

        loginAsaMechanicButton.setOnClickListener {
            startActivity(Intent(this, LoginAsAMechanic::class.java))
            finish()
        }

        loginAsAutomobileShopownerButton.setOnClickListener {
            startActivity(Intent(this, LoginAsAAutoshopOwner::class.java))
            finish()
        }
    }
}