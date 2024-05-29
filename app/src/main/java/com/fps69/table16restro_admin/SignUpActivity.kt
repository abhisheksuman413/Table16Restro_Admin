package com.fps69.table16restro_admin

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.fps69.table16restro_admin.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val locationList= arrayOf("jaipur","Aurangabad","Patna","Sikar")

        val adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,locationList)





        binding.apply {
            Tvalreadyhaveaccount.setOnClickListener {
                val intent =Intent(this@SignUpActivity,LoginActivity::class.java)
                startActivity(intent)
            }
            ListOfLocation.setAdapter(adapter)
            createButton.setOnClickListener {
                val intent =Intent(this@SignUpActivity,LoginActivity::class.java)
                startActivity(intent)
            }

        }
    }
}