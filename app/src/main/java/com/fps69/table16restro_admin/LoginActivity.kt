package com.fps69.table16restro_admin

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.fps69.table16restro_admin.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthCredential
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    //Var for googleSignin Clint
    private lateinit var googleSigninClint: GoogleSignInClient

    private lateinit var email: String
    private lateinit var password: String
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Auth
        auth = Firebase.auth
        // Initialize Firebase Database
        database = Firebase.database.getReference()

        // Initialize Google SignIn
        val GSO = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build()

        //Initialize Google SignIn
        googleSigninClint = GoogleSignIn.getClient(this@LoginActivity, GSO)



        binding.apply {
            TVnoaccount.setOnClickListener {
                val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
                startActivity(intent)
            }
            LoginButtonBTN.setOnClickListener {
                //Get text form edit text
                email = EmailAddressLoginET.text.toString().trim()
                password = PasswordLoginET.text.toString().trim()

                // Check if any field is empty
                if (email.isBlank() || password.isBlank()) {
                    Toast.makeText(
                        this@LoginActivity,
                        " Please fill All Details ",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    loginInAccount(email, password)
                }

            }

            LoginButtonGoogleBTN.setOnClickListener {
                val signIntent: Intent = googleSigninClint.signInIntent // Yha signIn Intent bna rhe hai
                launcher.launch(signIntent) // Isko use krte hai activity ko launch krne ke liye >>> Iske place pe phle onActivityResult ko use krte the
            }
        }
    }

    private fun loginInAccount(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { Task ->
            if (Task.isSuccessful) {
                Toast.makeText(this@LoginActivity, " Login Successful ", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(
                    this@LoginActivity,
                    " Login Failed ${Task.exception?.message}",
                    Toast.LENGTH_LONG
                ).show()
                Log.d("Login Account", "Login Failed ", Task.exception)
            }
        }

    }

    // This function for Google SignIn
    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            // Check If result is ok or not
            if (result.resultCode == Activity.RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                if (task.isSuccessful) {
                    val account: GoogleSignInAccount = task.result
                    val credential: AuthCredential =
                        GoogleAuthProvider.getCredential(account.idToken, null)
                    auth.signInWithCredential(credential).addOnCompleteListener { authTask ->
                        if (authTask.isSuccessful) {
                            // Successfully SignIn With Google
                            Toast.makeText(
                                this@LoginActivity,
                                " Login Successful With Google ",
                                Toast.LENGTH_LONG
                            ).show()
                            updateUi(authTask.result?.user)
                        } else {
                            Toast.makeText(
                                this@LoginActivity,
                                "Login Failed With Google ",
                                Toast.LENGTH_LONG
                            ).show()
                            Log.d("Login Google", "Login Field ", authTask.exception)
                        }
                    }
                } else {
                    Toast.makeText(
                        this@LoginActivity,
                        "Login Failed With Google ",
                        Toast.LENGTH_LONG
                    ).show()
                    Log.d("Login Google", "Login Field ", task.exception)
                }
            }
            else{
                Toast.makeText(
                    this@LoginActivity,
                    "Login Failed With Google In Result ",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    private fun updateUi(user: FirebaseUser?) {
        val intent =Intent(this,MainActivity::class.java)
        startActivity(intent)

    }

    // Check If user is already Login
    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val intent =Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}