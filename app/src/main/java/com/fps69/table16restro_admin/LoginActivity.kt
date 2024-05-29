package com.fps69.table16restro_admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fps69.table16restro_admin.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.apply {
            TVnoaccount.setOnClickListener {
                val intent=Intent(this@LoginActivity,SignUpActivity::class.java)
                startActivity(intent)
            }
            LoginButton.setOnClickListener {
                val intent=Intent(this@LoginActivity,MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}