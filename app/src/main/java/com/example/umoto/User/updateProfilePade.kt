package com.example.umoto.User

import android.app.Dialog
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Toast
import com.example.umoto.R
import com.example.umoto.databinding.ActivityUpdateProfilePadeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_update_profile_pade.*
import java.io.File

class updateProfilePade : AppCompatActivity() {
    private lateinit var dialog: Dialog

    private lateinit var binding: ActivityUpdateProfilePadeBinding
    private lateinit var auth :FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private lateinit var storageReference: StorageReference
    private lateinit var user: UserProfile
    private lateinit var uId:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateProfilePadeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        uId = auth.currentUser?.uid.toString()

        backButtontohome.setOnClickListener {
            startActivity(Intent(this,HomeScreen::class.java))
        }

        editProfile.setOnClickListener { 
            startActivity(Intent(this,profilepage::class.java))
            finish()
        }

        databaseReference = FirebaseDatabase.getInstance().getReference("Users")
        if (uId.isNotEmpty()){

            getUserdata()
        }

    }

    private fun getUserdata() {
        showProgressBar()
        databaseReference.child(uId).addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                user = snapshot.getValue(UserProfile::class.java)!!
                binding.updateuserProfileName.setText(user.userName)
                binding.upadteuserProfileemail.setText(user.email)
                binding.updateuserProfilemobileno.setText(user.mobileno)
                getUserProfile()
            }

            override fun onCancelled(error: DatabaseError) {
                hideProgressBar()

                Toast.makeText(this@updateProfilePade,"we failed to get data",Toast.LENGTH_SHORT).show()


            }


        })
    }

    private fun getUserProfile() {
        storageReference = FirebaseStorage.getInstance().reference.child("Users/$uId")

        val localFile = File.createTempFile("tempimage","jpg")
        storageReference.getFile(localFile).addOnSuccessListener {

            val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
            binding.firebaseimage.setImageBitmap(bitmap)
            hideProgressBar()

        }.addOnFailureListener{
            hideProgressBar()
            Toast.makeText(this,"We failed",Toast.LENGTH_SHORT).show()
        }
    }

    private fun showProgressBar(){
        dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_box)
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()
    }

    private fun hideProgressBar(){
        dialog.dismiss()
    }
}