package com.example.admincravecart

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import com.example.admincravecart.databinding.ActivityAddItemsBinding
import com.example.admincravecart.model.AllMenu
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage

class AddItemsActivity : AppCompatActivity() {
    //Food item details
    private lateinit var FoodName: String
    private lateinit var FoodPrice: String
    private lateinit var FoodDescription: String
    private lateinit var FoodIngredients: String
    private var FoodImageUri: Uri? = null

    //firebase
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase


    private val binding: ActivityAddItemsBinding by lazy {
        ActivityAddItemsBinding.inflate(
            layoutInflater
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        //initialize firebase
        auth = FirebaseAuth.getInstance()

        //Initialize firebase database

        database = FirebaseDatabase.getInstance()

        //Adding items
        binding.AddItemsButton.setOnClickListener {
            //Get data from fields
            FoodName = binding.AddItemFoodName.text.toString().trim()
            FoodPrice = binding.AddItemFoodPrice.text.toString().trim()
            FoodDescription = binding.AddItemFoodDescription.text.toString().trim()
            FoodIngredients = binding.AddItemFoodIngredients.text.toString().trim()

            if (!(FoodName.isBlank() || FoodPrice.isBlank() || FoodDescription.isBlank() || FoodIngredients.isBlank() )) {
                uploadData()
                Toast.makeText(this, "item added sucessfully", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "please fill all the details", Toast.LENGTH_SHORT).show()

            }
        }

        binding.AddItemFoodImage.setOnClickListener {
            pickImage.launch("image/*")
        }
        binding.AddItemBackButton.setOnClickListener { finish() }

    }

    private fun uploadData() {
        //get a reference to the menu node in the database
        val menuref = database.getReference("menu")
        //Generate the unique key for each menu item
        val menukey = menuref.push().key

        if (FoodImageUri != null) {
            val storageRef = FirebaseStorage.getInstance().reference
            val imageRef = storageRef.child("menu_images/${menukey}.jpg")
            val uploadTask = imageRef.putFile(FoodImageUri!!)

            uploadTask.addOnSuccessListener {
                imageRef.downloadUrl.addOnSuccessListener { downloadurl ->
                    //create new menu item
                    val newItem = AllMenu(
                        FoodName = FoodName,
                        FoodPrice = FoodPrice,
                        FoodDescription = FoodDescription,
                        FoodIngredients = FoodIngredients,
                        FoodImage = downloadurl.toString()

                    )
                    menukey?.let { key ->
                        menuref.child(key).setValue(newItem).addOnSuccessListener {
                            Toast.makeText(this, "Added item sucessfully", Toast.LENGTH_SHORT)
                                .show()

                        }
                            .addOnFailureListener {
                                Toast.makeText(this, "Add item failed", Toast.LENGTH_SHORT).show()

                            }
                    }
                }

            }.addOnFailureListener {
                Toast.makeText(this, "image upload failed", Toast.LENGTH_SHORT).show()
            }

        } else {
            Toast.makeText(this, "please select a image", Toast.LENGTH_SHORT).show()
        }
    }


    val pickImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        if (uri != null) {
            binding.SelectedImage.setImageURI(uri)
            FoodImageUri=uri
        }
    }
}