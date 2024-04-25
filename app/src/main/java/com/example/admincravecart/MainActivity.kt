package com.example.admincravecart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.admincravecart.databinding.ActivityAddItemsBinding
import com.example.admincravecart.databinding.ActivityMainBinding
import com.example.admincravecart.model.OrderDetails
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var database: FirebaseDatabase
    private lateinit var auth: FirebaseAuth
    private lateinit var completedOrderReference: DatabaseReference
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
        binding.EditProfile.setOnClickListener {
            val intent=Intent(this,AdminProfileActivity::class.java)
            startActivity(intent)
        }
        binding.AddNewUserButton.setOnClickListener {
            val intent=Intent(this,AddNewUserActivity::class.java)
            startActivity(intent)
        }
        binding.PendingOrdersButton.setOnClickListener {
            val intent=Intent(this,PendingOrderActivity::class.java)
            startActivity(intent)
        }
        binding.Logout.setOnClickListener {
            auth=FirebaseAuth.getInstance()
            auth.signOut()
            startActivity(Intent(this,SignupActivity::class.java))
            finish()
        }
        pendingOrders()
        completedOrders()
        wholeTimeEarning()
    }

    private fun wholeTimeEarning() {
        var listOfTotalPay= mutableListOf<Int>()

        completedOrderReference=FirebaseDatabase.getInstance().reference.child("completedOrder")
        var completedOrderItemCOunt=0
        completedOrderReference.addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
              for (orderSnapshot in snapshot.children){
                  var completeOrder=orderSnapshot.getValue(OrderDetails::class.java)

                  completeOrder?.totalPrice?.replace("$","")?.toIntOrNull()
                      ?.let {i->
                          listOfTotalPay.add(i)
                      }
              }
                binding.WholeTimeEarning.text=listOfTotalPay.sum().toString()+"Rs"
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    private fun completedOrders() {

        database= FirebaseDatabase.getInstance()
        var completedOrderReference=database.reference.child("completedOrder")
        var completedOrderItemCOunt=0
        completedOrderReference.addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                completedOrderItemCOunt=snapshot.childrenCount.toInt()
                binding.CompletedOrdersCOunt.text=completedOrderItemCOunt.toString()
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }


    private fun pendingOrders() {
        database= FirebaseDatabase.getInstance()
        var pendingOrderReference=database.reference.child("orderDetails")
        var pendingOrderItemCOunt=0
        pendingOrderReference.addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                pendingOrderItemCOunt=snapshot.childrenCount.toInt()
                binding.PendingOrderCount.text=pendingOrderItemCOunt.toString()
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
}