package com.example.umoto.User

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.umoto.MainActivity
import com.example.umoto.R
import kotlinx.android.synthetic.main.activity_slider_intro_main_file.*

class SliderIntroMainFile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slider_intro_main_file)

        nextBottom.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
    }
}