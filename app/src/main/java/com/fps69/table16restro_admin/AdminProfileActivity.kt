package com.fps69.table16restro_admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fps69.table16restro_admin.databinding.ActivityAdminProfileBinding

class AdminProfileActivity : AppCompatActivity() {

    private lateinit var binding:ActivityAdminProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAdminProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.apply {
            NameEditText.isEnabled=false
            AddressEditText.isEnabled=false
            EmailEditText.isEnabled=false
            PhoneEditText.isEnabled=false
            PasswordEditText.isEnabled=false



            var isEnable =false // Isko bna rhe hai because EditButton pe dubra click krne se fir se sara editText disabe ho jye
            EditButton.setOnClickListener {

                isEnable= !isEnable
                NameEditText.isEnabled=isEnable
                AddressEditText.isEnabled=isEnable
                EmailEditText.isEnabled=isEnable
                PhoneEditText.isEnabled=isEnable
                PasswordEditText.isEnabled=isEnable
                if(isEnable){
                    NameEditText.requestFocus() // Name pe keyboard open kra rhe hai
                }

            }
            BackButtonAdminProfile.setOnClickListener {
                finish()
            }
        }
    }
}