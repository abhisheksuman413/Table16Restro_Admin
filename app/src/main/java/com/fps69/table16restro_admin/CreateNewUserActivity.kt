package com.fps69.table16restro_admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fps69.table16restro_admin.databinding.ActivityCreateNewUserBinding

class CreateNewUserActivity : AppCompatActivity() {
    private lateinit var binding:ActivityCreateNewUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityCreateNewUserBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.apply {
            BackButtonCreateNewUser.setOnClickListener {
                finish()
            }
        }
    }
}