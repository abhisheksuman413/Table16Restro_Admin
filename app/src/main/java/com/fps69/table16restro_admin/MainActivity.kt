package com.fps69.table16restro_admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fps69.table16restro_admin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.apply {
            AddMenu.setOnClickListener {
                val intent = Intent(this@MainActivity, AddItemActivity::class.java)
                startActivity(intent)
            }
            AllItemMenu.setOnClickListener {
                val intent = Intent(this@MainActivity, AllItemActivity::class.java)
                startActivity(intent)
            }

            OutForDilevery.setOnClickListener {
                val intent = Intent(this@MainActivity, OutForDeliveryActivity::class.java)
                startActivity(intent)
            }

            Profile.setOnClickListener {
                val intent = Intent(this@MainActivity, AdminProfileActivity::class.java)
                startActivity(intent)
            }
            CreateNewUser.setOnClickListener {
                val intent = Intent(this@MainActivity, CreateNewUserActivity::class.java)
                startActivity(intent)


            }
        }
    }
}