package com.fps69.table16restro_admin.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.fps69.table16restro_admin.R
import com.fps69.table16restro_admin.databinding.ItemForPendingorderRecyclerviewBinding
import org.w3c.dom.Text

class PendingOrderAdapter(
    private val customerName: ArrayList<String>,
    private val orderQuantity: ArrayList<String>,
    private val pendingOrderImage: ArrayList<Int>,
    private val context:Context
) :
    RecyclerView.Adapter<PendingOrderAdapter.PendingOrderAdapterViewHolder>() {

    private val isAcceptedList = MutableList(customerName.size) { true }


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
                PendingOrderCustomerName.text = customerName[position].toString()
                PendingOrderQuantityNumber.text = orderQuantity[position].toString()
                PendingOrderFoodImage.setImageResource(pendingOrderImage[position].toInt())


                val isAccepted = isAcceptedList[position]
                AcceptOrDispatchButton.apply {
                    text = if (isAccepted) {
                        "Accept"
                    } else {
                        "Dispatch"
                    }
                    setTextColor(
                        ContextCompat.getColor(
                            context,
                            if (isAccepted) R.color.white else R.color.Green
                        )
                    )
                    setOnClickListener {
                        if (isAccepted) {
                            text = "Dispatch"
                            isAcceptedList[position] = false
                            notifyItemChanged(position)
                            showToast("Order is Accepted")
                        }
                        else{
                            removeItem(adapterPosition)
                            showToast("Order is Dispatch")
                        }
                    }
                }

            }

        }

        private fun removeItem(adapterPositionn: Int) {
            customerName.removeAt(adapterPositionn)
            orderQuantity.removeAt(adapterPositionn)
            pendingOrderImage.removeAt(adapterPositionn)
            isAcceptedList.removeAt(adapterPositionn)
            notifyItemRemoved(adapterPositionn)
            notifyItemRangeChanged(adapterPositionn, customerName.size)

        }

        private fun showToast(message: String) {
            Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
        }

    }
}