package com.fps69.table16restro_admin.Adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fps69.table16restro_admin.ModelClass.AllMenu
import com.fps69.table16restro_admin.databinding.ItemForAllitemRecyclerviewBinding
import com.google.firebase.database.DatabaseReference

class AllItemAdapter(
    private val context: Context,
    private val mutableProductList: ArrayList<AllMenu>,
    databaseReference: DatabaseReference
) :
    RecyclerView.Adapter<AllItemAdapter.AllItemAdapterViewHolder>() {

    private val itemQuantities = IntArray(mutableProductList.size) { 1 }
//    private val  = menuList.toMutableList()


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AllItemAdapterViewHolder {
        val binding = ItemForAllitemRecyclerviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return AllItemAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AllItemAdapterViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return mutableProductList.size
    }


    inner class AllItemAdapterViewHolder(private val binding: ItemForAllitemRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply {
                val quantitites = itemQuantities[position]
                val menuItem=mutableProductList[position]
//                val uriString = mutableProductList[position].foodImag.toString()
//                val uri=Uri.parse(uriString)  >>> When we pass uri to glide it handel both uri and url aslo
                                            // >>> Here we are convert url into uri(String to URI)

                quantity.text=quantitites.toString()
                AllItemFoodName.text = menuItem.foodName.toString()
                AllItemFoodPrice.text = "$${menuItem.foodPrice.toString()}"
                Glide.with(context).load("${menuItem.foodImag.toString()}")
                    .into(binding.AllItemImage)

                minusbutton.setOnClickListener {
                    decreas(position)

                }
                plusbutton.setOnClickListener {
                    increase(position)

                }
                deletebutton.setOnClickListener {
                    delete(position)

                }

            }
        }

        private fun decreas(position: Int) {
            if (itemQuantities[position] > 1) {
                itemQuantities[position]--
                binding.quantity.text = itemQuantities[position].toString()

            }
        }

        private fun increase(position: Int) {
            if (itemQuantities[position] < 9) {
                itemQuantities[position]++
                binding.quantity.text = itemQuantities[position].toString()

            }

        }

        private fun delete(position: Int) {
            mutableProductList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position,mutableProductList.size)


        }

    }
}