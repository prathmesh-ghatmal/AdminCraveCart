package com.example.admincravecart.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.admincravecart.databinding.PendingorderitemBinding

class PendingOrderAdapter(
    private val context: Context,
    private val customerNames: MutableList<String>,
    private val quantities: MutableList<String>,
    private val foodInages: MutableList<String>,
    private val itemclicked:OnItemClicked

) : RecyclerView.Adapter<PendingOrderAdapter.PendingViewholder>() {

interface OnItemClicked{
    fun onItemClickListner(position: Int)
    fun onItemAccceptedListener(position: Int)
    fun onItemDispatchedListner(position: Int)
}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PendingViewholder {
     val binding=PendingorderitemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PendingViewholder(binding)
    }


    override fun onBindViewHolder(holder: PendingViewholder, position: Int) {
holder.bind(position)
    }

    override fun getItemCount(): Int =customerNames.size
    inner class PendingViewholder(private val binding: PendingorderitemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var isAccepted=false
        fun bind(position: Int) {
            binding.apply {
                CustomerName.text=customerNames[position]
                Quantity.text=quantities[position]
                var uri=Uri.parse(foodInages[position])
                Glide.with(context).load(uri).into(PendingOrderImage)
                PendingOrderbutton.apply {
                if(!isAccepted){text="Accept"} else{text="Dispatch"}
                    setOnClickListener {
                        if (!isAccepted){text="Dispatch"
                            isAccepted=true
                            ShowToast("Order is Accepted")
                            itemclicked.onItemAccceptedListener(position)
                        }else{
                            customerNames.removeAt(adapterPosition)
                            notifyItemRemoved(adapterPosition)
                            ShowToast("Order is Dispatched")
                            itemclicked.onItemDispatchedListner(position)
                        }
                    }
                }
                itemView.setOnClickListener{itemclicked.onItemClickListner(position)}
            }

        }
      private  fun ShowToast(message:String){
            Toast.makeText(context,message,Toast.LENGTH_LONG).show()
        }

    }
}