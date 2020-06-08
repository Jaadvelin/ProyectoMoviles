package com.relaxingproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.settings_screen.*

class SettingsActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_screen)

        auth = FirebaseAuth.getInstance()

        logoutBtn.setOnClickListener{
            doLogout()
        }
    }

    private fun doLogout() {
        auth.signOut()
        startActivity(Intent(this, LoginActivity::class.java))
        //updateUI(user)
    }

    private fun updateUI(currentUser: FirebaseUser?){

        if(currentUser != null){
            if(currentUser.isEmailVerified){
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }else {
                Toast.makeText(baseContext, "Please verify your email.",
                    Toast.LENGTH_SHORT).show()
            }
        } else{
            Toast.makeText(baseContext, "Login failed.",
                Toast.LENGTH_SHORT).show()
        }
    }
}