package com.fps69.table16restro_admin.Adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.fps69.table16restro_admin.R
import com.fps69.table16restro_admin.databinding.ItemForOutfordeliveryRecyclerViewBinding

class OutForDeliveryAdapter(
    private val customerName: ArrayList<String>,
    private val moneyStatus: ArrayList<String>
) :
    RecyclerView.Adapter<OutForDeliveryAdapter.OutForDeliveryAdapterViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OutForDeliveryAdapterViewHolder {
        val binding = ItemForOutfordeliveryRecyclerViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return OutForDeliveryAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: OutForDeliveryAdapterViewHolder,
        position: Int
    ) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {

        return customerName.size

    }


    inner class OutForDeliveryAdapterViewHolder(private val binding: ItemForOutfordeliveryRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {

            binding.apply {
                CustomerName.text=customerName[position].toString()
                PaymentStatus.text=moneyStatus[position].toString()
            }



            // Ase v kr skte hai ya niche dono wale trika se v kr skte hai
            val colorMap= mapOf(
                "Received" to Color.GREEN,"Not Received" to Color.RED,"Pending" to Color.YELLOW
            )

            binding.PaymentStatus.setTextColor(colorMap[moneyStatus[position]]?:Color.BLACK)  // Yha black default color dal rhe hai
            binding.Indicator.setCardBackgroundColor(colorMap[moneyStatus[position]]?:Color.BLACK)  // Yha black default color dal rhe hai






            /*
            // Ase v kr skte hai ya niche wale trika se v kr skte hai


            val Context= binding.Indicator.context
            val color= if(moneyStatus[position]=="Not Received "){
                ContextCompat.getColor(Context,R.color.textColour)
            }
            else if(moneyStatus[position]=="Pending"){
                ContextCompat.getColor(Context,R.color.Yellow)
            }
            else{
                ContextCompat.getColor(Context,R.color.Green)
            }

            binding.Indicator.setCardBackgroundColor(color)
            binding.PaymentStatus.setTextColor(color)

             */




            /*
            // This is another way to code above code
            if(moneyStatus[position]=="Not Received "){
                binding.Indicator.setCardBackgroundColor(ContextCompat.getColor(binding.Indicator.context, R.color.textColour))
                binding.PaymentStatus.setTextColor(ContextCompat.getColor(binding.Indicator.context, R.color.textColour))
            }
            else if(moneyStatus[position]=="Pending"){
                binding.Indicator.setCardBackgroundColor(ContextCompat.getColor(binding.Indicator.context,R.color.Yellow))
                binding.PaymentStatus.setTextColor(ContextCompat.getColor(binding.Indicator.context, R.color.Yellow))
            }
            else{
                binding.Indicator.setCardBackgroundColor(ContextCompat.getColor(binding.Indicator.context,R.color.Green))
                binding.PaymentStatus.setTextColor(ContextCompat.getColor(binding.Indicator.context, R.color.Green))
            }
            */

        }

    }
}