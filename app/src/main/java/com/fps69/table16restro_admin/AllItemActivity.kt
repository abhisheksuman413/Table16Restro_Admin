package com.fps69.table16restro_admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.fps69.table16restro_admin.Adapter.AllItemAdapter
import com.fps69.table16restro_admin.ModelClass.AllMenu
import com.fps69.table16restro_admin.databinding.ActivityAllItemBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AllItemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAllItemBinding
    private lateinit var databaseReference: DatabaseReference
    private lateinit var database: FirebaseDatabase
    private var itemList: ArrayList<AllMenu> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseReference = FirebaseDatabase.getInstance().reference

        retrievemenuItem()

        binding.BackButtonAllItem.setOnClickListener {
            finish()
        }


//        InitRetrofil()


    }

    private fun retrievemenuItem() {
        database = FirebaseDatabase.getInstance()
        val foodRef = database.reference.child("menu")
        //Fetch  data from database
        foodRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // Clear existing data before insert
                itemList.clear()
                for (item in snapshot.children) {
                    val dummeyUser = item.getValue(AllMenu::class.java)
                    dummeyUser?.let {
                        itemList.add(it)
                    }
                }
                InitRecyclerView()

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(
                    this@AllItemActivity,
                    "Cannot Show item Internal Error ",
                    Toast.LENGTH_LONG
                ).show()
                Log.d(
                    "Get Data ",
                    "Cannot Show item Internal Error ${error.message}"
                )

            }


        })

    }

//    private fun InitRetrofil() {
//        val retrofitBuilder = Retrofit.Builder()
//            .baseUrl("https://dummyjson.com/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(ApiDummyDataInterface::class.java)
//
//
//        val retrofilData =  retrofitBuilder.getProductData()
//
//        retrofilData.enqueue(object: Callback<DummeyUserData?> {
//            override fun onResponse(p0: Call<DummeyUserData?>, p1: Response<DummeyUserData?>) {
//                // If Api call is success
//                val responseBody =p1.body()
//                val productList = responseBody?.recipes!!
//
//
//
//                InitRecyclerView(productList)
//
//
//            }
//
//            override fun onFailure(p0: Call<DummeyUserData?>, p2: Throwable) {
//                // If API call fails
//                Log.d("main Activity ","Error happen"+ p2.message)
//            }
//
//
//        })
//    }


    private fun InitRecyclerView() {

        val Adapter = AllItemAdapter(this, itemList,databaseReference)
        binding.AllItemRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.AllItemRecyclerView.adapter = Adapter

    }
}