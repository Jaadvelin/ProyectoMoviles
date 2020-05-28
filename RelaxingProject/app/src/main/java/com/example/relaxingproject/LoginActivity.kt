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


const val RC_SIGN_IN = 123
class LoginActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth= FirebaseAuth.getInstance()

        regButton.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }

        logButton.setOnClickListener{
            doLogin()
        }
    }

    private fun doLogin(){
        if(txtUser.text.toString().isEmpty()){
            txtUser.error = "Please enter email"
            txtUser.requestFocus()
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
        //comentario random
        auth.signInWithEmailAndPassword(txtUser.text.toString(), txtPassword.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    updateUI(null)
                }
            }
    }

    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        updateUI(currentUser)
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
