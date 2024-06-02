package com.fps69.table16restro_admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.fps69.table16restro_admin.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding


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
                    loginInAccount(email,password)
                }

            }
        }
    }1`

    private fun loginInAccount(email: String, password: String) {
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener { Task ->
            if(Task.isSuccessful){
                Toast.makeText(this@LoginActivity," Login Successful ", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this@LoginActivity," Login Failed ${Task.exception?.message}",Toast.LENGTH_LONG).show()
                Log.d("Login Account","Login Failed ", Task.exception)
            }
        }

    }
}