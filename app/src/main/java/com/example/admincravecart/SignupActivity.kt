package com.example.admincravecart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.admincravecart.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {
    private val binding: ActivitySignupBinding by lazy {
        ActivitySignupBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.CreateAccountButton.setOnClickListener{
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        binding.SignInAlreadyHaveAc.setOnClickListener{
            val intent= Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
        val locationlist= arrayOf("jaipur","mumbai","delhi","pune")
        val adapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,locationlist)
        val autoCompleteTextView=binding.ListofLocation
        autoCompleteTextView.setAdapter(adapter)
    }
}