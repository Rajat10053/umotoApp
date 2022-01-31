package com.example.umoto.Autoshopowner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.umoto.R
import kotlinx.android.synthetic.main.activity_final_login_as_autoshopowner.*

class finalLoginAsAutoshopowner : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final_login_as_autoshopowner)

        finalloginAsAutoShopOwnerSignupButton.setOnClickListener {
            startActivity(Intent(this,LoginAsAAutoshopOwner::class.java))
        }
    }
}