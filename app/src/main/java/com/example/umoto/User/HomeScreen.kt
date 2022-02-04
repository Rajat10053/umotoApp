package com.example.umoto.User

import android.R.menu
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.example.umoto.R
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_home_screen.*


class HomeScreen : AppCompatActivity() {
    lateinit var toggle:ActionBarDrawerToggle

    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        val menuToUse = R.menu.my_right_side_menu

        val drawerlayout:DrawerLayout = findViewById(R.id.drawerLayout)
        val navigationview: NavigationView = findViewById(R.id.naviagtionView)

        toggle = ActionBarDrawerToggle(this,drawerlayout,R.string.open,R.string.close)
        drawerlayout.addDrawerListener(toggle)

        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowCustomEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)


        navigationview.setNavigationItemSelectedListener {
            when(it.itemId){

                R.id.nev_home -> Toast.makeText(this,"Clicked Home",Toast.LENGTH_LONG).show()
                R.id.nev_profile -> {
                    startActivity(Intent(this,updateProfilePade::class.java))
                    finish()
                }
                R.id.nev_paymentDetail -> Toast.makeText(this,"Clicked payment",Toast.LENGTH_LONG).show()
                R.id.nev_notification -> Toast.makeText(this,"Clicked notifiaction",Toast.LENGTH_LONG).show()
                R.id.nev_trackmechanic -> {
                    //startActivity(Intent(this,ConfirmPeople::class.java))
                    //finish()
                }
                R.id.nev_aboutus -> Toast.makeText(this,"Clicked aboutus",Toast.LENGTH_LONG).show()
                R.id.nev_logout -> {
                    Firebase.auth.signOut()
                    startActivity(Intent(this,finalLoginUser::class.java))
                    finish()
                }

            }
            true
        }

        mAuth = FirebaseAuth.getInstance()

        button.setOnClickListener {
            Firebase.auth.signOut()


        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}