package com.fps69.table16restro_admin

import android.R
import android.accounts.Account
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import com.fps69.table16restro_admin.ModelClass.UserData
import com.fps69.table16restro_admin.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.jar.Attributes.Name

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    // Create some var for signUP
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var userName: String
    private lateinit var nameOfResturent: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val locationList = arrayOf("jaipur", "Aurangabad", "Patna", "Sikar")

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, locationList)


        // Initialize Firebase Auth
        auth = Firebase.auth
        // Initialize Firebase Databse
        database = Firebase.database.reference





        binding.apply {
            Tvalreadyhaveaccount.setOnClickListener {
                val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
                startActivity(intent)
            }
            ListOfLocation.setAdapter(adapter)

            // What happen When Create button click
            createButtonBTN.setOnClickListener {

                // Get text from EDIT_TEXT
                email = binding.emailET.text.toString().trim()
                password = binding.passwordET.text.toString().trim()
                nameOfResturent = binding.NameofRestaurantET.text.toString().trim()
                userName = binding.NameofOwnerET.text.toString().trim()

                // Cheack if any field is empty
                if (email.isBlank() || password.isBlank() || nameOfResturent.isBlank() || userName.isBlank()) {
                    Toast.makeText(
                        this@SignUpActivity,
                        "Please Fill All Details",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    // Agr koi field blanck nhi hua to ye function call ho jyega
                    createAccount(
                        email,
                        password
                    )  // Function call for create account(Niche create kiye hai )
                }


            }

        }
    }

    // This Function is created for create user account through Email and Password
    private fun createAccount(email: String, password: String) {

        /*
        >>> Create auth var of type FirebaseAuth and
            Initialize Database var with Firebase.auth
        */
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task -> // Yha task ke name se lemda function bna rhe hai
                if (task.isSuccessful) {
                    Toast.makeText(
                        this@SignUpActivity,
                        " Account Created Successfully ",
                        Toast.LENGTH_SHORT
                    ).show()
                    saveUserData() // This function is call for save user data in Firebase realTime Database
                    val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()

                } else {
                    Toast.makeText(
                        this@SignUpActivity,
                        " Account Creation Failed ${task.exception?.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.d("Account", "Account Creation Failed", task.exception)
                }
            }
    }

    // This function is created for save userData in realTime Database(Firebase)
    private fun saveUserData() {
        /*
        >>> Create Database var of type DatabaseReference and
            Initialize Database var with Firebase.database.reference
        >>> 1st hame eak data class banana hai
        >>> Eak unique userId create krege */

        email = binding.emailET.text.toString().trim()
        password = binding.passwordET.text.toString().trim()
        nameOfResturent = binding.NameofRestaurantET.text.toString().trim()
        userName = binding.NameofOwnerET.text.toString().trim()

        val user = UserData(userName, nameOfResturent, email, password)

        val userId: String =
            FirebaseAuth.getInstance().currentUser!!.uid // Iss se unique UserId create ho jyega

        // Save user data into firebase database
        database.child("user").child(userId).setValue(user) // Yha first node use id ka hai
    }
}