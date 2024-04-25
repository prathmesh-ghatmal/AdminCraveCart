package com.example.admincravecart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.admincravecart.adapter.OrdetDetailsAdapter
import com.example.admincravecart.databinding.ActivityOrderDetailsBinding
import com.example.admincravecart.model.OrderDetails

class OrderDetailsActivity : AppCompatActivity() {
    private val binding:ActivityOrderDetailsBinding by lazy { ActivityOrderDetailsBinding.inflate(layoutInflater) }
    private var userName:String?=null
    private var address:String?=null
    private var phoneNumber:String?=null
    private var totalPrice:String?=null
    private var foodNames:ArrayList<String> = arrayListOf()
    private var foodImages:ArrayList<String> = arrayListOf()
    private var foodQuantity:ArrayList<Int> = arrayListOf()
    private var foodPrices:ArrayList<String> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.OrderDetailesBackButton.setOnClickListener { finish() }

        getDataFromIntent()
    }

    private fun getDataFromIntent() {
       val recivedOrderDetails=intent.getSerializableExtra("userOrderDetails") as OrderDetails
        recivedOrderDetails?.let { orderDetails ->
            userName=recivedOrderDetails.userName
            address=recivedOrderDetails.address
            phoneNumber=recivedOrderDetails.phoneNumber
            totalPrice=recivedOrderDetails.totalPrice
            foodNames= recivedOrderDetails.foodNames as ArrayList<String>
            foodQuantity= recivedOrderDetails.foodQuantities as ArrayList<Int>
            foodImages=recivedOrderDetails.foodImages as ArrayList<String>
            foodPrices=recivedOrderDetails.foodPrices as ArrayList<String>
            setUserDetails()
            setAdapter()
        }

    }



    private fun setUserDetails() {
        binding.PayoutName.text=userName
        binding.PayoutAddress.text=address
        binding.PayoutPhone.text=phoneNumber
        binding.PayOutTotalPrice.text=totalPrice
    }
    private fun setAdapter() {
        binding.OrderDetailsRecyclerView.layoutManager=LinearLayoutManager(this)
        val adapter=OrdetDetailsAdapter(this,foodNames,foodImages,foodQuantity,foodPrices)
        binding.OrderDetailsRecyclerView.adapter=adapter
    }
}