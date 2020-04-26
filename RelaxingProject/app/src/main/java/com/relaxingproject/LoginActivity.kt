package com.relaxingproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*


const val RC_SIGN_IN = 123
class LoginActivity : AppCompatActivity() {

    private lateinit var txtUser: EditText
    private lateinit var txtPassword: EditText
    private lateinit var progressBar: ProgressBar
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        /*// Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        val gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()
        // Build a GoogleSignInClient with the options specified by gso.
        val mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        sign_in_button.visibility = View.VISIBLE
        sign_in_button.setOnClickListener{
            val signInIntent = mGoogleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }*/

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        txtUser = findViewById(R.id.txtUser)
        txtPassword = findViewById(R.id.txtPassword)
        progressBar= findViewById(R.id.progressBar)
        auth= FirebaseAuth.getInstance()
    }

    fun forgotPassword(view:View){

    }
    fun register(view:View){
        startActivity(Intent(this, RegisterActivity::class.java))
    }
    fun login(view:View){
        loginUser()
    }

    private fun loginUser(){
        val user:String = txtUser.text.toString()
        val password:String = txtPassword.text.toString()

        if(!TextUtils.isEmpty(user) && !TextUtils.isEmpty(password)){
            progressBar.visibility = View.VISIBLE

            auth.signInWithEmailAndPassword(user, password).addOnCompleteListener(this){
                task ->

                if(task.isSuccessful){
                    action()
                }else{
                    Toast.makeText(this, "Error en la autenticación", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun action(){
        startActivity(Intent(this, MainActivity::class.java))
    }

    /*override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task =
                GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }*/

    /*private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account =
                completedTask.getResult(ApiException::class.java)

            // Signed in successfully, show authenticated UI.

        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            sign_in_button.visibility = View.VISIBLE
        }
    }*/
}
