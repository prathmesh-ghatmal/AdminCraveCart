package com.example.admincravecart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.admincravecart.adapter.DeliveryAdapter
import com.example.admincravecart.databinding.ActivityOutForDeliveryBinding

class OutForDeliveryActivity : AppCompatActivity() {
    private val binding:ActivityOutForDeliveryBinding by lazy { ActivityOutForDeliveryBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val customername= arrayListOf("jpm","ktm","duke")
        val MoneyStatus= arrayListOf("Recived","NotRecived","Pending")
        val adapter=DeliveryAdapter(customername,MoneyStatus)
        binding.DeliveryRecyclerView.adapter=adapter
        binding.DeliveryRecyclerView.layoutManager=LinearLayoutManager(this)
    }
}