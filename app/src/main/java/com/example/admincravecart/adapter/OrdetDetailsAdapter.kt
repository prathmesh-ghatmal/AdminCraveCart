package com.example.admincravecart.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.admincravecart.databinding.OrderdetailitemBinding


class OrdetDetailsAdapter(
    private var context: Context,
    private var foodName: ArrayList<String>,
    private var foodImage: ArrayList<String>,
    private var foodQuantity: ArrayList<Int>,
    private var foodprice: ArrayList<String>,
): RecyclerView.Adapter<OrdetDetailsAdapter.OrderDetailsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderDetailsViewHolder {
       val binding=OrderdetailitemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return OrderDetailsViewHolder(binding)
    }


    override fun onBindViewHolder(holder: OrderDetailsViewHolder, position: Int) {
       holder.bind(position)
    }
    override fun getItemCount(): Int=foodName.size

 inner   class OrderDetailsViewHolder (private val binding:OrderdetailitemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int) {
          binding.apply {
              OrdeDetailCustomerName.text=foodName[position]
              OrderDetailPrice.text=foodprice[position]
              ORderDetailQuantity.text=foodQuantity[position].toString()
              val uriString=foodImage[position]
              val uri=Uri.parse(uriString)
              Glide.with(context).load(uri).into(OrderDetailsFoodImage)
          }
        }

    }
}