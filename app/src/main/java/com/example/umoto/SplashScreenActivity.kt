package com.example.umoto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.core.content.withStyledAttributes
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

//        val handler = Handler(Looper.getMainLooper())
//        handler.postDelayed({
//            startActivity(Intent(this,MainActivity::class.java))
//            finish()
//        },3000)

        iv_note.alpha = 0f
        iv_note.animate().setDuration(2000).alpha(1f).withEndAction {
            val i = Intent(this,MainActivity::class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()
        }
    }
}




