package com.fps69.table16restro_admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.fps69.table16restro_admin.Adapter.OutForDeliveryAdapter
import com.fps69.table16restro_admin.databinding.ActivityOutForDeliveryBinding

class OutForDeliveryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOutForDeliveryBinding


    private lateinit var customerName: ArrayList<String>
    private lateinit var moneyStatus: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityOutForDeliveryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        customerName= arrayListOf(
            "John Doe", "Jane Smith", "Alice Johnson", "Bob Brown",
            "Charlie Davis", "Diana Evans", "Eve Foster", "Frank Green",
            "Grace Harris", "Hank Irwin", "Ivy Jones", "Jack King",
            "Kelly Lee", "Liam Martin", "Mia Nelson", "Nathan Owens",
            "Olivia Parker", "Paul Quinn", "Quincy Reed", "Rachel Scott",
            "Samuel Turner", "Tina Underwood", "Uma Vance", "Victor White",
            "Wendy Xiong", "Xander Young", "Yvonne Zimmerman", "Zachary Adams",
            "Andrea Baker", "Brian Collins", "Christine Delgado", "Daniel Edwards",
            "Ella Fitzgerald", "Fiona Grant", "George Hall", "Hannah Iverson",
            "Ian Jenkins", "Julia Keaton", "Kevin Lewis", "Laura Moore",
            "Michael Norton", "Nina Owens", "Oscar Perez", "Pamela Quinn",
            "Robert Smith", "Sophia Turner", "Thomas Underwood", "Ursula Vance",
            "Vincent Wallace", "Walter Xavier", "Xenia Young", "Yusuf Zimmerman",
            "Zara Anderson", "Adam Brown", "Betty Clark", "Catherine Davis",
            "David Evans", "Elena Foster", "Frank Grant", "Gina Harris",
            "Harry Irwin", "Isla Jones", "Jack King", "Kara Lee"

        )

        moneyStatus= arrayListOf(
            "Not Received", "Received", "Pending", "Received",
            "Pending", "Not Received", "Received", "Not Received",
            "Pending", "Received", "Not Received", "Pending",
            "Received", "Not Received", "Pending", "Received",
            "Not Received", "Pending", "Received", "Not Received",
            "Pending", "Received", "Not Received", "Pending",
            "Received", "Not Received", "Pending", "Received",
            "Not Received", "Pending", "Received", "Not Received",
            "Pending", "Received", "Not Received", "Pending",
            "Received", "Not Received", "Pending", "Received",
            "Not Received", "Pending", "Received", "Not Received",
            "Pending", "Received", "Not Received", "Pending",
            "Received", "Not Received", "Pending", "Received",
            "Not Received", "Pending", "Received", "Not Received",
            "Pending", "Received", "Not Received", "Pending",
            "Received", "Not Received", "Pending", "Received"
        )


        binding.BackButtonOutForDelivery.setOnClickListener {
            finish()
        }



        val Adapter = OutForDeliveryAdapter(customerName,moneyStatus)
        binding.RecyclerViewOutForDelivery.layoutManager=LinearLayoutManager(this)
        binding.RecyclerViewOutForDelivery.adapter=Adapter


    }
}