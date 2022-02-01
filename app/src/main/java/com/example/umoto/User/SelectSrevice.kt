package com.example.umoto.User

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import com.example.umoto.R
import com.example.umoto.databinding.ActivitySelectSreviceBinding

class SelectSrevice : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_srevice)

        val languages = resources.getStringArray(R.array.Cities)



        val autocompleteTV = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)




    }
}