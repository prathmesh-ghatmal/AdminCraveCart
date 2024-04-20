package com.example.admincravecart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.admincravecart.adapter.AddItemAdapter
import com.example.admincravecart.databinding.ActivityAllItemsBinding
import com.example.admincravecart.model.AllMenu
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AllItemsActivity : AppCompatActivity() {
      private lateinit var databaseReference: DatabaseReference
      private lateinit var database: FirebaseDatabase
      private  var menuItems: ArrayList<AllMenu> = ArrayList()
    private val binding:ActivityAllItemsBinding by lazy { ActivityAllItemsBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
       databaseReference=FirebaseDatabase.getInstance().reference
        retriveMenuItem()

        binding.AllItemBackButton.setOnClickListener { finish() }

    }

    private fun retriveMenuItem() {
        database = FirebaseDatabase.getInstance()
        val foodRef: DatabaseReference= database.reference.child("menu")

        //Fetching data from database
        foodRef.addListenerForSingleValueEvent(object :ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                //Clear existing data before populating
                menuItems.clear()

                //loop through each food item
                for (foodSnapshot in snapshot.children){
                    val menuItem=foodSnapshot.getValue(AllMenu::class.java)

                    menuItem?.let {
                         menuItems.add(it)
                    }
                }
                setAdapter()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("DatabaseError","Error${error.message}")
            }


        })

    }
    private fun setAdapter() {
        val adapter=AddItemAdapter(this@AllItemsActivity,menuItems,databaseReference)
        binding.MenuRecyclerView.layoutManager= LinearLayoutManager(this)
        binding.MenuRecyclerView.adapter=adapter
    }



}


