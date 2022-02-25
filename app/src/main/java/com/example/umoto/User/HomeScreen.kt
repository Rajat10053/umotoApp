package com.example.umoto.User

import android.Manifest
import android.R.menu
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.umoto.R
import com.example.umoto.databinding.ActivityHomeScreenBinding
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_home_screen.*
import kotlinx.android.synthetic.main.nev_hader.*
import java.io.File


class HomeScreen : AppCompatActivity() {
    private lateinit var permissionlauncher: ActivityResultLauncher<Array<String>>
    private var isLocationpermission = false

    private var isstoragepermission = false
    lateinit var toggle:ActionBarDrawerToggle
    private lateinit var binding: ActivityHomeScreenBinding

    private lateinit var auth : FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private lateinit var storageReference: StorageReference
    private lateinit var user: UserProfile
    private lateinit var uId:String


    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        permissionlauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){
            permissions ->
            isstoragepermission = permissions[Manifest.permission.READ_EXTERNAL_STORAGE] ?: isstoragepermission
            isLocationpermission = permissions[Manifest.permission.ACCESS_FINE_LOCATION] ?: isLocationpermission

        }
        requestPermission()

        val drawerlayout:DrawerLayout = findViewById(R.id.drawerLayout)
        val navigationview: NavigationView = findViewById(R.id.naviagtionView)

        toggle = ActionBarDrawerToggle(this,drawerlayout,R.string.open,R.string.close)
        drawerlayout.addDrawerListener(toggle)

        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowCustomEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)

        auth = FirebaseAuth.getInstance()


        uId = auth.currentUser?.uid.toString()

        databaseReference = FirebaseDatabase.getInstance().getReference("Users")
        if (uId.isNotEmpty()){

            getUserdata()
        }


        navigationview.setNavigationItemSelectedListener {
            when(it.itemId){

                R.id.nev_home -> Toast.makeText(this,"Clicked Home",Toast.LENGTH_LONG).show()
                R.id.nev_profile -> {
                    startActivity(Intent(this,updateProfilePade::class.java))
                    finish()
                }
                R.id.nev_paymentDetail -> Toast.makeText(this,"Clicked payment",Toast.LENGTH_LONG).show()
                R.id.nev_notification -> {
                    startActivity(Intent(this,MapsActivity::class.java))
                    finish()
                }
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

        card.setOnClickListener {
            when(it.id){

                R.id.generalServices -> {
                    startActivity(Intent(this,MapsActivity::class.java))
                    finish()
                }
                R.id.fullbodypaint ->{
                    startActivity(Intent(this,MapsActivity::class.java))
                    finish()
                }
                R.id.completcarspa ->{
                    startActivity(Intent(this,MapsActivity::class.java))
                    finish()
                }
                R.id.acServices ->{
                    startActivity(Intent(this,MapsActivity::class.java))
                    finish()
                }
                R.id.wheelalignment ->{
                    startActivity(Intent(this,MapsActivity::class.java))
                    finish()
                }
                R.id.scratchremoval ->{
                    startActivity(Intent(this,MapsActivity::class.java))
                    finish()
                }
                R.id.interiroDetailing ->{
                    startActivity(Intent(this,MapsActivity::class.java))
                    finish()
                }
                R.id.enginecheckup ->{
                    startActivity(Intent(this,MapsActivity::class.java))
                    finish()
                }
                R.id.waterwash ->{
                    startActivity(Intent(this,MapsActivity::class.java))
                    finish()
                }
                R.id.emergencyServices ->{
                    startActivity(Intent(this,MapsActivity::class.java))
                    finish()
                }

            }
        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getUserdata() {

        val navigationview: NavigationView = findViewById(R.id.naviagtionView)

        var nav = navigationview.getHeaderView(0)
        var naav = nav.findViewById<TextView>(R.id.usernavaaahe)

        databaseReference.child(uId).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                user = snapshot.getValue(UserProfile::class.java)!!
                naav.setText(user.userName)
                usernoaahe.setText(user.mobileno)
                getUserProfile()
            }

            override fun onCancelled(error: DatabaseError) {


                Toast.makeText(this@HomeScreen,"we failed to get data", Toast.LENGTH_SHORT).show()


            }


        })
    }

    private fun getUserProfile() {
        storageReference = FirebaseStorage.getInstance().reference.child("Users/$uId")

        val localFile = File.createTempFile("tempimage","jpg")
        storageReference.getFile(localFile).addOnSuccessListener {

            val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
            chotonav.setImageBitmap(bitmap)

        }.addOnFailureListener{

            Toast.makeText(this,"We failed",Toast.LENGTH_SHORT).show()
        }
    }

    private fun requestPermission(){
        isstoragepermission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED

        isLocationpermission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val permissionRequest : MutableList<String> = ArrayList()

        if (!isLocationpermission){
            permissionRequest.add(Manifest.permission.ACCESS_FINE_LOCATION)
        }
        if (!isstoragepermission){
            permissionRequest.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        }

        if (permissionRequest.isNotEmpty()){
            permissionlauncher.launch(permissionRequest.toTypedArray())
        }

    }


}