package com.fps69.table16restro_admin.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fps69.table16restro_admin.databinding.ItemForPendingorderRecyclerviewBinding

class PendingOrderAdapter(
    private val customerName: ArrayList<String>,
    private val orderQuantity: ArrayList<String>,
    private val pendingOrderImage: ArrayList<Int>
) :
    RecyclerView.Adapter<PendingOrderAdapter.PendingOrderAdapterViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PendingOrderAdapterViewHolder {
        val binding = ItemForPendingorderRecyclerviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PendingOrderAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: PendingOrderAdapterViewHolder,
        position: Int
    ) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return customerName.size
    }


    inner class PendingOrderAdapterViewHolder(private var binding: ItemForPendingorderRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply {
                PendingOrderCustomerName.text=customerName[position].toString()
                PendingOrderQuantityNumber.text=orderQuantity[position].toString()
                PendingOrderFoodImage.setImageResource(pendingOrderImage[position].toInt())

            }

        }

    }
}