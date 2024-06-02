package com.fps69.table16restro_admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.fps69.table16restro_admin.Adapter.PendingOrderAdapter
import com.fps69.table16restro_admin.databinding.ActivityPendingOrderBinding

class PendingOrderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPendingOrderBinding


    private lateinit var customerName: ArrayList<String>
    private lateinit var orderQuantity: ArrayList<String>
    private lateinit var pendingOrderImage: ArrayList<Int>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityPendingOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)


        customerName = arrayListOf(
            "John Doe", "Jane Smith", "Alice Johnson", "Bob Brown",
            "Michael White", "Sarah Black", "Chris Green", "Emma Blue",
            "David Grey", "Sophia Red", "Liam Young", "Olivia Pink",
            "Noah Orange", "Ava Purple", "William Cyan", "Isabella Yellow",
            "James Maroon", "Mia Silver", "Lucas Gold", "Amelia Violet",
            "Henry Indigo", "Charlotte Teal", "Benjamin Magenta", "Evelyn Lavender",
            "Alexander Navy", "Abigail Turquoise", "Mason Plum", "Emily Coral",
            "Elijah Mint", "Harper Peach"
        )

        orderQuantity = arrayListOf(
            "2", "3", "1", "5",
            "4", "6", "2", "3",
            "1", "7", "2", "4",
            "5", "3", "6", "1",
            "3", "2", "4", "5",
            "2", "6", "1", "3",
            "4", "5", "6", "2",
            "3", "1"
        )

        pendingOrderImage = arrayListOf(
            R.drawable.itemimage,
            R.drawable.itemimage,
            R.drawable.itemimage,
            R.drawable.itemimage,
            R.drawable.itemimage,
            R.drawable.itemimage,
            R.drawable.itemimage,
            R.drawable.itemimage,
            R.drawable.itemimage,
            R.drawable.itemimage,
            R.drawable.itemimage,
            R.drawable.itemimage,
            R.drawable.itemimage,
            R.drawable.itemimage,
            R.drawable.itemimage,
            R.drawable.itemimage,
            R.drawable.itemimage,
            R.drawable.itemimage,
            R.drawable.itemimage,
            R.drawable.itemimage,
            R.drawable.itemimage,
            R.drawable.itemimage,
            R.drawable.itemimage,
            R.drawable.itemimage,
            R.drawable.itemimage,
            R.drawable.itemimage,
            R.drawable.itemimage,
            R.drawable.itemimage,
            R.drawable.itemimage,
            R.drawable.itemimage,
        )

        val Adapter= PendingOrderAdapter(customerName,orderQuantity,pendingOrderImage,this)


        binding.apply {
            RecyclerViewPendingOrders.layoutManager=LinearLayoutManager(this@PendingOrderActivity)
            RecyclerViewPendingOrders.adapter=Adapter

        }
    }
}