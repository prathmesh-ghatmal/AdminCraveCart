package com.example.admincravecart.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.admincravecart.databinding.DeliveryitemBinding

class DeliveryAdapter(private val CustomerName:ArrayList<String>,private val MoneyStatus:ArrayList<String>):RecyclerView.Adapter<DeliveryAdapter.DeliveryViewholder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeliveryViewholder {
       val binding=DeliveryitemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return DeliveryViewholder(binding)
    }



    override fun onBindViewHolder(holder: DeliveryViewholder, position: Int) {
       holder.bind(position)
    }
    override fun getItemCount(): Int =CustomerName.size
    inner class DeliveryViewholder (private val binding: DeliveryitemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int) {
            binding.apply {
                DeliveryCustomerName.text=CustomerName[position]
                DeliveryPaymentStatus.text=MoneyStatus[position]
                val colorMap= mapOf("Recived" to Color.GREEN,"NotRecived" to Color.RED,"Pending" to Color.GRAY)
                DeliveryPaymentStatus.setTextColor(colorMap[MoneyStatus[position]]?:Color.BLACK)
                DeliveryDtatus.backgroundTintList= ColorStateList.valueOf(colorMap[MoneyStatus[position]]?:Color.BLACK)
            }
        }

    }
}