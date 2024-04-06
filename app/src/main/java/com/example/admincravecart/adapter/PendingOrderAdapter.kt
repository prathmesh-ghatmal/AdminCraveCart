package com.example.admincravecart.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.admincravecart.databinding.PendingorderitemBinding

class PendingOrderAdapter(
    private val customerNames: ArrayList<String>,
    private val quantities: ArrayList<String>,
    private val foodInages: ArrayList<Int>,
    private val context: Context
) : RecyclerView.Adapter<PendingOrderAdapter.PendingViewholder>() {


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
                PendingOrderImage.setImageResource(foodInages[position])
                PendingOrderbutton.apply {
                if(!isAccepted){text="Accept"} else{text="Dispatch"}
                    setOnClickListener {
                        if (!isAccepted){text="Dispatch"
                            isAccepted=true
                            ShowToast("Order is Accepted")
                        }else{
                            customerNames.removeAt(adapterPosition)
                            notifyItemRemoved(adapterPosition)
                            ShowToast("Order is Dispatched")
                        }
                    }
                }
            }

        }
      private  fun ShowToast(message:String){
            Toast.makeText(context,message,Toast.LENGTH_LONG).show()
        }

    }
}