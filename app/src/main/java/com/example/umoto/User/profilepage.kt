package com.example.umoto.User

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.i
import android.view.LayoutInflater
import android.view.Window
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.umoto.R
import com.example.umoto.databinding.ActivityProfilepageBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_profilepage.*
import java.net.URI
import java.util.*

class profilepage : AppCompatActivity() {

    lateinit var imageUri:Uri
    private lateinit var storage: StorageReference
    private lateinit var binding: ActivityProfilepageBinding
    private lateinit var auth:FirebaseAuth
    private lateinit var databaseReference: DatabaseReference

    private lateinit var dialog: Dialog

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfilepageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        val uId = auth.currentUser?.uid

        databaseReference = FirebaseDatabase.getInstance().getReference("Users")

        binding.SelectImege.setOnClickListener {
            selectImage()
        }

        logoutprofile.setOnClickListener {
            startActivity(Intent(this,finalLoginUser::class.java))
            finish()
        }

        backButton.setOnClickListener {
            startActivity(Intent(this,HomeScreen::class.java))
        }



        binding.Save.setOnClickListener {
            showProgressBar()

            val userName = binding.userProfileName.text.toString()
            val email = binding.userProfileemail.text.toString()
            val mobileno = binding.userProfilemobileno.text.toString()
            val Password = binding.userProfilpassword.text.toString()

            val user = UserProfile(userName,email,mobileno,Password)
            if (uId != null){
                databaseReference.child(uId).setValue(user).addOnCompleteListener {
                    if (it.isSuccessful){
                        uplodeImage()

                        databaseReference = FirebaseDatabase.getInstance().reference
                            .child("Users").child(userName!!)

                        var userObject = HashMap<String, Any>()
                        userObject.put("display_name",userName)
                        userObject.put("mobileNo", mobileno)
                        userObject.put("email", email)
                        userObject.put("pasword", Password)

                        databaseReference.setValue(userObject)





                    }else{
                        hideProgressBar()
                        Toast.makeText(this,"Failed to update",Toast.LENGTH_LONG).show()
                    }
                }
            }



            startActivity(Intent(this,HomeScreen::class.java))
           finish()
        }
    }



    private fun uplodeImage() {
        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Uploading file.....")
        progressDialog.setCancelable(false)
        progressDialog.show()

        //see if we can use normal simpledata

        val formatter = java.text.SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
        val now = Date()
        val fileName = formatter.format(now)

        val storageReference = FirebaseStorage.getInstance().getReference("Users/"+auth.currentUser?.uid)

        storageReference.putFile(imageUri).addOnSuccessListener {
            binding.faceimage.setImageURI(null)
            hideProgressBar()
            Toast.makeText(this,"Image Uploaded",Toast.LENGTH_LONG).show()


        }.addOnFailureListener {
            hideProgressBar()
            Toast.makeText(this,"Image failed",Toast.LENGTH_LONG).show()

        }

    }

    private fun selectImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(intent,100)


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 100 && resultCode == RESULT_OK){

              imageUri = data?.data!!
            binding.faceimage.setImageURI(imageUri)


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