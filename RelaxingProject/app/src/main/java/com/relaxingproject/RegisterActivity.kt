package com.relaxingproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.txtPassword
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var progressBar:ProgressBar
    private lateinit var database:FirebaseDatabase
    private lateinit var auth:FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        progressBar= findViewById(R.id.progressBar)
        database= FirebaseDatabase.getInstance()
        auth= FirebaseAuth.getInstance()

        register.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser(){
        if(txtEmail.text.toString().isEmpty()){
            txtEmail.error = "Please enter email"
            txtEmail.requestFocus()
            return
        }

        /*if(Patterns.EMAIL_ADDRESS.matcher(txtEmail.text.toString()).matches()){
            txtEmail.error = "Please enter valid email"
            txtEmail.requestFocus()
            return
        }*/

        if(txtPassword.text.toString().isEmpty()){
            txtPassword.error = "Please enter password"
            txtPassword.requestFocus()
            return
        }

        auth.createUserWithEmailAndPassword(txtEmail.text.toString(), txtPassword.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    user?.sendEmailVerification()
                        ?.addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                startActivity(Intent(this, LoginActivity::class.java))
                                finish()
                            }
                        }
                } else {
                    Toast.makeText(baseContext, "Register failed. Try again after some time",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }
}
