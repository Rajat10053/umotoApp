package com.example.umoto.User

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.umoto.R
import kotlinx.android.synthetic.main.activity_login_as_auser.*

class signUpAsAUserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_as_auser)



        loginAsUserLoginButton.setOnClickListener {
            startActivity(Intent(this,finalLoginUser::class.java))
            finish()
        }
    }
}