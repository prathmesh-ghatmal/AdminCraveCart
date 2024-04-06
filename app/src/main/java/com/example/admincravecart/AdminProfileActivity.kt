package com.example.admincravecart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.admincravecart.databinding.ActivityAdminProfileBinding

class AdminProfileActivity : AppCompatActivity() {
    private val binding:ActivityAdminProfileBinding by lazy { ActivityAdminProfileBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.Name.isEnabled=false
        binding.Address.isEnabled=false
        binding.Email.isEnabled=false
        binding.Phone.isEnabled=false
        binding . Password . isEnabled =false
        binding.AdminProfileBackButton.setOnClickListener { finish() }
        var isEnable=false
        binding.EditButton.setOnClickListener {
            isEnable=!isEnable
            binding.Name.isEnabled=isEnable
            binding.Address.isEnabled=isEnable
            binding.Email.isEnabled=isEnable
            binding.Phone.isEnabled=isEnable
            binding . Password . isEnabled =isEnable
            if(isEnable){
              binding.Name.requestFocus()
            }
        }
    }
}