package com.example.admincravecart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.admincravecart.databinding.ActivityAddNewUserBinding

class AddNewUserActivity : AppCompatActivity() {
    private val binding:ActivityAddNewUserBinding by lazy { ActivityAddNewUserBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.AddNewUserBackButton.setOnClickListener { finish() }
    }
}