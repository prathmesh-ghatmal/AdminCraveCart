package com.example.admincravecart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import com.example.admincravecart.databinding.ActivityAddItemsBinding

class AddItemsActivity : AppCompatActivity() {
    private val binding:ActivityAddItemsBinding by lazy { ActivityAddItemsBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.AddItemImage.setOnClickListener {
            pickimage.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
        binding.AddItemBackButton.setOnClickListener { finish() }
    }
    val pickimage=registerForActivityResult(ActivityResultContracts.PickVisualMedia()){
        uri->if (uri!=null){
            binding.SelectedImage.setImageURI(uri)
    }
    }
}