package com.fps69.table16restro_admin.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fps69.table16restro_admin.RecipeDummyUserData
import com.fps69.table16restro_admin.databinding.ActivityAllItemBinding
import com.fps69.table16restro_admin.databinding.ItemForAllitemRecyclerviewBinding
import kotlin.coroutines.coroutineContext

class AllItemAdapter(private val productList: List<RecipeDummyUserData>) :
    RecyclerView.Adapter<AllItemAdapter.AllItemAdapterViewHolder>() {

    private val itemQuantities = IntArray(productList.size) { 1 }
    private val mutableProductList = productList.toMutableList()


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
                quantity.text=quantitites.toString()
                AllItemFoodName.text = mutableProductList[position].name.toString()
                AllItemFoodPrice.text = "$${mutableProductList[position].prepTimeMinutes.toString()}"

                Glide.with(binding.root).load(mutableProductList[position].image.toString())
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