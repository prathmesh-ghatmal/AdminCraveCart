package com.example.admincravecart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.admincravecart.databinding.ActivityAddItemsBinding
import com.example.admincravecart.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.AddMenuButton.setOnClickListener {
            val intent=Intent(this,AddItemsActivity::class.java)
            startActivity(intent)
        }
        binding.AllMenuButton.setOnClickListener {
            val intent=Intent(this,AllItemsActivity::class.java)
            startActivity(intent)
        }
        binding.OrderDispatch.setOnClickListener {
            val intent=Intent(this,OutForDeliveryActivity::class.java)
            startActivity(intent)
        }
    }
}