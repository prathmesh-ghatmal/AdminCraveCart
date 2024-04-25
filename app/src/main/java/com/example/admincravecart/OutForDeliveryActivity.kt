package com.example.admincravecart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.admincravecart.adapter.DeliveryAdapter
import com.example.admincravecart.databinding.ActivityOutForDeliveryBinding
import com.example.admincravecart.model.OrderDetails
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class OutForDeliveryActivity : AppCompatActivity() {
    private val binding:ActivityOutForDeliveryBinding by lazy { ActivityOutForDeliveryBinding.inflate(layoutInflater) }
    private lateinit var database: FirebaseDatabase
    private var listOfCOmpleteOrder:ArrayList<OrderDetails> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.OutForDeliveryBackButton.setOnClickListener { finish() }

        //retrive and display completed orders
        retriveCompletedOrderDetails()


    }

    private fun retriveCompletedOrderDetails() {
        //initialize firebase database
        database=FirebaseDatabase.getInstance()
        val completedOrderReference=database.reference.child("completedOrder").orderByChild("currentTime")
        completedOrderReference.addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                //clear the list before populating new data
                listOfCOmpleteOrder.clear()
                for(ordersnapshot in snapshot.children){
                    val completeOrder=ordersnapshot.getValue(OrderDetails::class.java)
                    completeOrder?.let {
                        listOfCOmpleteOrder.add(it)
                    }
                }
                listOfCOmpleteOrder.reverse()
                setDataIntoRecyclerView()
            }



            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
    private fun setDataIntoRecyclerView() {
      //initialization list to hold customers name and payment status
        val customerName= mutableListOf<String>()
        val moneyStatus= mutableListOf<Boolean>()

        for (order in listOfCOmpleteOrder){
            order.userName?.let {
                customerName.add(it)
            }
            moneyStatus.add(order.paymentRecived!!)
        }
        val adapter=DeliveryAdapter(customerName,moneyStatus)
        binding.DeliveryRecyclerView.adapter=adapter
        binding.DeliveryRecyclerView.layoutManager=LinearLayoutManager(this)
    }
}