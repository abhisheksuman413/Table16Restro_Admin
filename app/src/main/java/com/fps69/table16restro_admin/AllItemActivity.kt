package com.fps69.table16restro_admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.fps69.table16restro_admin.Adapter.AllItemAdapter
import com.fps69.table16restro_admin.databinding.ActivityAllItemBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AllItemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAllItemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAllItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.BackButtonAllItem.setOnClickListener {
            finish()
        }



        InitRetrofil()




    }

    private fun InitRetrofil() {
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiDummyDataInterface::class.java)


        val retrofilData =  retrofitBuilder.getProductData()

        retrofilData.enqueue(object: Callback<DummeyUserData?> {
            override fun onResponse(p0: Call<DummeyUserData?>, p1: Response<DummeyUserData?>) {
                // If Api call is success
                val responseBody =p1.body()
                val productList = responseBody?.recipes!!



                InitRecyclerView(productList)


            }

            override fun onFailure(p0: Call<DummeyUserData?>, p2: Throwable) {
                // If API call fails
                Log.d("main Activity ","Error happen"+ p2.message)
            }


        })
    }


    private fun InitRecyclerView(productList: List<RecipeDummyUserData>){

        val Adapter = AllItemAdapter(productList)
        binding.AllItemRecyclerView.layoutManager=LinearLayoutManager(this)
        binding.AllItemRecyclerView.adapter= Adapter

    }
}