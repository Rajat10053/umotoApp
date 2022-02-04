package com.example.umoto.User

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.umoto.R
import com.example.umoto.databinding.ActivityLoginAsAuserBinding
import com.example.umoto.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_login_as_auser.*
import java.time.chrono.JapaneseEra.values

class LoginUpAsAUserActivity : AppCompatActivity() {
    companion object {
        const val RC_SIGN_IN = 120
    }

    private lateinit var mAuth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var mDatabase: DatabaseReference
    private lateinit var binding : ActivityLoginAsAuserBinding
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_as_auser)


        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("308317209428-n0n7pfhdbii7sc46qkrgs3gf8ihq8bqj.apps.googleusercontent.com")
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)

        //Firebase Auth instance
        mAuth = FirebaseAuth.getInstance()

        loginAsUsergoogleButton.setOnClickListener {

            signIn()


        }


        loginAsUserLoginButton.setOnClickListener {
            startActivity(Intent(this,finalLoginUser::class.java))
            finish()
        }

        loginAsUsersignUpButton.setOnClickListener {

            var email = loginAsUserEmailId.text.toString().trim()
            var password = loginAsUserPassword.text.toString().trim()
            var displayName = loginAsUserFullName.text.toString().trim()
            var mobileNo = loginAsUserMobileNumber.text.toString().trim()

            if (!TextUtils.isEmpty(email) || !TextUtils.isEmpty(password) || !TextUtils.isEmpty(displayName) ||!TextUtils.isEmpty(mobileNo)){
                createAccount(email,password,displayName,mobileNo)
                Log.d("it is working ",displayName)
            }else{
                Toast.makeText(this,"Please fill out everything",Toast.LENGTH_LONG).show()
            }
        }

    }
    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val exception = task.exception
            if (task.isSuccessful) {
                try {
                    // Google Sign In was successful, authenticate with Firebase
                    val account = task.getResult(ApiException::class.java)!!
                    Log.d("SignInActivity", "firebaseAuthWithGoogle:" + account.id)
                    firebaseAuthWithGoogle(account.idToken!!)
                } catch (e: ApiException) {
                    // Google Sign In failed, update UI appropriately
                    Log.w("SignInActivity", "Google sign in failed", e)
                }
            } else {
                Log.w("SignInActivity", exception.toString())
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("SignInActivity", "signInWithCredential:success")
                    val intent = Intent(this, profilepage::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.d("SignInActivity", "signInWithCredential:failure")
                }
            }
    }

    fun createAccount(email: String, password:String,userName:String,mobileno:String){
        mAuth?.createUserWithEmailAndPassword(email,password)
            ?.addOnCompleteListener {
                    task: Task<AuthResult> ->
                if (task.isSuccessful){
                    var currentUser = mAuth!!.currentUser
                    var userId = currentUser?.uid
                    mDatabase = FirebaseDatabase.getInstance().reference
                        .child("Users").child(userName!!)

                    var userObject = HashMap<String, Any>()
                    userObject.put("display_name",userName)
                    userObject.put("mobileNo", mobileno)


                    mDatabase.setValue(userObject).addOnCompleteListener {
                            task: Task<Void> ->
                        if (task.isSuccessful) {
                            var dashboardIntent = Intent(this, finalLoginUser::class.java)
                            dashboardIntent.putExtra("name", userName)
                            dashboardIntent.putExtra("mobile no",mobileno)
                            dashboardIntent.putExtra("email",email)
                            Log.d("myintent",mobileno)
                            startActivity(dashboardIntent)
                            finish()


                        }else {

                            Toast.makeText(this, "User Not Created!", Toast.LENGTH_LONG)
                                .show()

                        }
                    }

                }else{}
            }
    }


}


