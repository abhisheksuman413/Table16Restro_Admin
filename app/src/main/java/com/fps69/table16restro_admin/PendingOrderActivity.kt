package com.fps69.table16restro_admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fps69.table16restro_admin.databinding.ActivityPendingOrderBinding

class PendingOrderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPendingOrderBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityPendingOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {

        }
    }
}