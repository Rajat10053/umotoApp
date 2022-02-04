package com.example.umoto.Autoshopowner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.umoto.Mechanic.AcceptThejob
import com.example.umoto.R
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_final_login_as_autoshopowner.*
import kotlinx.android.synthetic.main.activity_final_login_as_mechanic.*

class finalLoginAsAutoshopowner : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final_login_as_autoshopowner)

        mAuth = FirebaseAuth.getInstance()

        finalloginAsAutoShopOwnersignUpButton.setOnClickListener {

            var email = finalloginAsAutoShopOwnerEmailId.text
            var password = finalloginAsAutoShopOwnerPassword.text

            if ( !TextUtils.isEmpty(password.toString())){
                loginUser(email.toString(),password.toString())
            }else{
                Toast.makeText(this,"please sir fill every thing ",Toast.LENGTH_LONG).show()
            }
        }

        finalloginAsAutoShopOwnerSignupButton.setOnClickListener {
            startActivity(Intent(this,LoginAsAAutoshopOwner::class.java))
        }
    }

    private fun loginUser(email: String, password: String) {

        mAuth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener {
                    task: Task<AuthResult> ->
                if (task.isSuccessful){

                    var dashboardIntent = Intent(this, JobForAutomobile::class.java)

                    startActivity(dashboardIntent)
                    finish()
                }else{
                    Toast.makeText(this,"User Not Created", Toast.LENGTH_LONG).show()
                }

            }

    }
}