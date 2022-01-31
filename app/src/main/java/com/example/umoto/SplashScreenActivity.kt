package com.example.umoto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.core.content.withStyledAttributes
import com.example.umoto.User.HomeScreen
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

//        val handler = Handler(Looper.getMainLooper())
//        handler.postDelayed({
//            startActivity(Intent(this,MainActivity::class.java))
//            finish()
//        },3000)
        mAuth = FirebaseAuth.getInstance()
        val user = mAuth.currentUser
        Log.d("I am still here",user.toString())



        iv_note.alpha = 0f
        iv_note.animate().setDuration(2000).alpha(1f).withEndAction {
            if(user != null){
                Log.d("Oh man I am here",user.toString())
                startActivity(Intent(this,HomeScreen::class.java))
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
                finish()

            }else{val i = Intent(this,MainActivity::class.java)
                startActivity(i)
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
                finish()}

        }
    }
}




