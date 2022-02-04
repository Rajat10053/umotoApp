package com.example.umoto.Mechanic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.umoto.R
import com.example.umoto.User.HomeScreen
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_final_login_as_mechanic.*
import kotlinx.android.synthetic.main.activity_final_login_user.*

class finalLoginAsMechanic : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final_login_as_mechanic)

        mAuth = FirebaseAuth.getInstance()

        finalloginAsMechanicSignupButton.setOnClickListener {
            startActivity(Intent(this,LoginAsAMechanic::class.java))
            finish()


        }
        finalloginAsMechanicsignUpButton.setOnClickListener {

            var email = finalloginAsMechanicEmailId.text
            var password = finalloginAsMechanicPassword.text

            if ( !TextUtils.isEmpty(password.toString())){
                loginUser(email.toString(),password.toString())
            }else{
                Toast.makeText(this,"please fill every thing  ",Toast.LENGTH_LONG).show()
            }


        }


    }

    private fun loginUser(email: String, password: String) {

        mAuth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener {
                    task: Task<AuthResult> ->
                if (task.isSuccessful){

                    var dashboardIntent = Intent(this, AcceptThejob::class.java)

                    startActivity(dashboardIntent)
                    finish()
                }else{
                    Toast.makeText(this,"User Not Created", Toast.LENGTH_LONG).show()
                }

            }

    }
}