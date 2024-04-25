package com.example.admincravecart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.admincravecart.adapter.PendingOrderAdapter
import com.example.admincravecart.databinding.ActivityPendingOrderBinding
import com.example.admincravecart.model.OrderDetails
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class PendingOrderActivity : AppCompatActivity(), PendingOrderAdapter.OnItemClicked {
    private lateinit var binding: ActivityPendingOrderBinding
    private var listofName: MutableList<String> = mutableListOf()
    private var listofTotalPrice: MutableList<String> = mutableListOf()
    private var listofImageFirstFoodOrder: MutableList<String> = mutableListOf()
    private var listofOrderItem: ArrayList<OrderDetails> = arrayListOf()
    private lateinit var database: FirebaseDatabase
    private lateinit var databaseOrderDetilas: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPendingOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //initoialization of firebase
        database = FirebaseDatabase.getInstance()
        databaseOrderDetilas = database.reference.child("orderDetails")

        getorderDetials()
        binding.PendingOrdersBackButton.setOnClickListener { finish() }


    }

    private fun getorderDetials() {
        databaseOrderDetilas.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (orderSnapshot in snapshot.children) {
                    val orderDetails = orderSnapshot.getValue(OrderDetails::class.java)
                    orderDetails?.let { listofOrderItem.add(it) }
                }
                addDataTOListForRecyclerView()
            }


            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    private fun addDataTOListForRecyclerView() {
        for (orderItem in listofOrderItem) {
            //add data to respective list for populating the recycler view
            orderItem.userName?.let { listofName.add(it) }
            orderItem.totalPrice?.let { listofTotalPrice.add(it) }
            orderItem.foodImages?.filterNot { it.isEmpty() }?.forEach {
                listofImageFirstFoodOrder.add(it)
            }
        }
        setAdapter()
    }

    private fun setAdapter() {

        binding.PendingOrderRecyclerView.layoutManager = LinearLayoutManager(this)
        val adapter =
            PendingOrderAdapter(this, listofName, listofTotalPrice, listofImageFirstFoodOrder, this)
        binding.PendingOrderRecyclerView.adapter = adapter
    }

    override fun onItemClickListner(position: Int) {
        val intent = Intent(this, OrderDetailsActivity::class.java)
        val userOrderDetails = listofOrderItem[position]
        intent.putExtra("userOrderDetails", userOrderDetails)
        startActivity(intent)
    }

    override fun onItemAccceptedListener(position: Int) {
        //handle item acceptance and update database
        val childItemPushKey = listofOrderItem[position].itemPushKey
        val clickItemOrderReference = childItemPushKey?.let {
            database.reference.child("orderDetails").child(it)
        }
        clickItemOrderReference?.child("orderAccepted")?.setValue(true)
        updateOrderAcceptedStatus(position)
    }


    override fun onItemDispatchedListner(position: Int) {
        val dispatchItemPushKey=listofOrderItem[position].itemPushKey
        val dispatchItemOrderReference=database.reference.child("completedOrder").child(dispatchItemPushKey!!)
        dispatchItemOrderReference.setValue(listofOrderItem[position])
            .addOnSuccessListener {
                deleteThisItemFromOrderDetails(dispatchItemPushKey)
            }
    }

    private fun deleteThisItemFromOrderDetails(dispatchItemPushKey: String) {
        val orderDetailsItemReference=database.reference.child("orderDetails").child(dispatchItemPushKey)
        orderDetailsItemReference.removeValue()
            .addOnSuccessListener {
                Toast.makeText(this, "Order is Dispatched", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Order is not Dispatched", Toast.LENGTH_SHORT).show()

            }
    }

    private fun updateOrderAcceptedStatus(position: Int) {
        //u[date order acceptance in users buy history and database
        val userIdofClickedItem = listofOrderItem[position].userUid
        val pushKeyOfClickedItem = listofOrderItem[position].itemPushKey
        val buyHistoryReference =
            database.reference.child("user").child(userIdofClickedItem!!).child("BuyHistory")
                .child(pushKeyOfClickedItem!!)
        buyHistoryReference.child("orderAccepted").setValue(true)
        databaseOrderDetilas.child(pushKeyOfClickedItem).child("orderAccepted").setValue(true)
    }
}