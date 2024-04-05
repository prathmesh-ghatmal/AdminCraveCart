package com.example.admincravecart.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.admincravecart.databinding.ItemMenuBinding

class AllItemAdapter (
    private val AddItemName: MutableList<String>,
    private val AddItemPrice: MutableList<String>,
    private val AddItemImage: MutableList<Int>
) : RecyclerView.Adapter<AllItemAdapter.AddAllItemViewHolder>() {

    private val itemQuantities = IntArray(AddItemName.size) { 1 }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddAllItemViewHolder {
        val binding = ItemMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AddAllItemViewHolder(binding)
    }


    override fun onBindViewHolder(holder: AddAllItemViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = AddItemName.size

    inner class AddAllItemViewHolder(private val binding: ItemMenuBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply {
                val quantity = itemQuantities[position]
                MenuItemName.text = AddItemName[position]
                MenuItemPrice.text = AddItemPrice[position]
                MenuItemImage.setImageResource(AddItemImage[position])
                foodquantity.text = quantity.toString()

                minusbutton.setOnClickListener { decreasequantity(position) }
                plusbutton.setOnClickListener { increasequantity(position) }
                deletebutton.setOnClickListener {
                    val itemPosition = adapterPosition
                    if (itemPosition != RecyclerView.NO_POSITION) {
                        deleteitem(itemPosition)
                    }
                }

            }
        }
        private fun increasequantity(position: Int){
            if(itemQuantities[position]<10){
                itemQuantities[position]++
                binding.foodquantity.text=itemQuantities[position].toString()
            }
        }
        private fun decreasequantity(position: Int){
            if(itemQuantities[position]>1){
                itemQuantities[position]--
                binding.foodquantity.text=itemQuantities[position].toString()
            }
        }
        private fun deleteitem(position: Int){
            AddItemName.removeAt(position)
            AddItemImage.removeAt(position)
            AddItemPrice.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position,AddItemName.size)
        }
    }
}