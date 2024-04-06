package com.example.admincravecart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.admincravecart.adapter.PendingOrderAdapter
import com.example.admincravecart.databinding.ActivityPendingOrderBinding

class PendingOrderActivity : AppCompatActivity() {
    private val binding:ActivityPendingOrderBinding by lazy { ActivityPendingOrderBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.PendingOrdersBackButton.setOnClickListener { finish() }
        val customername= arrayListOf("burger","pizza","momo","mocha","sandwich","gg")
        val quantity= arrayListOf("Rs25","Rs85","Rs95","Rs125","Rs15","Rs75")
        val foodimage= arrayListOf(
            R.drawable.menu1,
            R.drawable.menu2,
            R.drawable.menu3,
            R.drawable.menu4,
            R.drawable.menu5,
            R.drawable.menu5
        )
        val adapter=PendingOrderAdapter(customername,quantity,foodimage,this)
        binding.PendingOrderRecyclerView.adapter=adapter
        binding.PendingOrderRecyclerView.layoutManager=LinearLayoutManager(this)
    }
}