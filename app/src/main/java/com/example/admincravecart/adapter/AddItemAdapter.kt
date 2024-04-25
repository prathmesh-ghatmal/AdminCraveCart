package com.example.admincravecart.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.admincravecart.databinding.ItemMenuBinding
import com.example.admincravecart.model.AllMenu
import com.google.firebase.database.DatabaseReference

class AddItemAdapter(
    private val context: Context,
    private val menulist: ArrayList<AllMenu>,
    databaseReference: DatabaseReference,
private val onDeleteClickListner:(position:Int)->Unit  ) : RecyclerView.Adapter<AddItemAdapter.AddAllItemViewHolder>() {

    private val itemQuantities = IntArray(menulist.size) { 1 }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddAllItemViewHolder {
        val binding = ItemMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AddAllItemViewHolder(binding)
    }


    override fun onBindViewHolder(holder: AddAllItemViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = menulist.size

    inner class AddAllItemViewHolder(private val binding: ItemMenuBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply {
                val quantity = itemQuantities[position]
                val menuItem=menulist[position]
                val uriString=menuItem.foodImage

                val uri=Uri.parse(uriString)
              //  if (uri==null){
                   // Toast.makeText(context,"null",Toast.LENGTH_SHORT).show()}
                MenuItemName.text = menuItem.foodName
                MenuItemPrice.text = menuItem.foodPrice
                Glide.with(context).load(uri).into(MenuItemImage)
                foodquantity.text = quantity.toString()

                minusbutton.setOnClickListener { decreasequantity(position) }
                plusbutton.setOnClickListener { increasequantity(position) }
                deletebutton.setOnClickListener {
                    onDeleteClickListner(position)

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
            menulist.removeAt(position)
            menulist.removeAt(position)
            menulist.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position,menulist.size)
        }
    }
}