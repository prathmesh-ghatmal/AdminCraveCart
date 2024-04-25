package com.example.admincravecart.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.admincravecart.databinding.DeliveryitemBinding

class DeliveryAdapter(private val CustomerName:MutableList<String>,private val MoneyStatus:MutableList<Boolean>):RecyclerView.Adapter<DeliveryAdapter.DeliveryViewholder>() {


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
                if(MoneyStatus [position]== true){
                    DeliveryPaymentStatus.text="Recived"
                }else{
                    DeliveryPaymentStatus.text="not recived"
                }

                val colorMap= mapOf(true to Color.GREEN,false to Color.RED,"Pending" to Color.GRAY)
                DeliveryPaymentStatus.setTextColor(colorMap[MoneyStatus[position]]?:Color.BLACK)
                DeliveryDtatus.backgroundTintList= ColorStateList.valueOf(colorMap[MoneyStatus[position]]?:Color.BLACK)
            }
        }

    }
}