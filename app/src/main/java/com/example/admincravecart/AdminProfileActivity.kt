package com.example.admincravecart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.admincravecart.databinding.ActivityAdminProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import java.util.jar.Attributes.Name

class AdminProfileActivity : AppCompatActivity() {
    private val binding:ActivityAdminProfileBinding by lazy { ActivityAdminProfileBinding.inflate(layoutInflater) }
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var adminReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        //initialization
        auth=FirebaseAuth.getInstance()
        database= FirebaseDatabase.getInstance()
        adminReference=database.reference.child("user")
        binding.Name.isEnabled=false
        binding.Address.isEnabled=false
        binding.Email.isEnabled=false
        binding.Phone.isEnabled=false
        binding . Password . isEnabled =false
        binding.AminSaveInformationButton.isEnabled=false
        binding.AdminProfileBackButton.setOnClickListener { finish() }
        binding.AminSaveInformationButton.setOnClickListener { updateUserData() }
        var isEnable=false
        binding.EditButton.setOnClickListener {
            isEnable=!isEnable
            binding.Name.isEnabled=isEnable
            binding.Address.isEnabled=isEnable
            binding.Email.isEnabled=isEnable
            binding.Phone.isEnabled=isEnable
            binding . Password . isEnabled =isEnable
            binding.AminSaveInformationButton.isEnabled=isEnable
            if(isEnable){
              binding.Name.requestFocus()
            }
        }
        retriveUserData()
    }



    private fun retriveUserData() {
        val currentUserUid=auth.currentUser?.uid
        if(currentUserUid!=null){
            val userReference=adminReference.child(currentUserUid)
            userReference.addListenerForSingleValueEvent(object :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists()){
                        var ownerName=snapshot.child("name").getValue()
                        var email=snapshot.child("email").getValue()
                        var address=snapshot.child("address").getValue()
                        var phone=snapshot.child("phone").getValue()
                        setDataTOTextView(ownerName,email,address,phone)
                    }
                }



                override fun onCancelled(error: DatabaseError) {

                }
            })
        }


    }
    private fun setDataTOTextView(
        ownerName: Any?,
        email: Any?,
        address: Any?,
        phone: Any?
    ) {
        binding.Name.setText(ownerName.toString())
        binding.Email.setText(email.toString())
        binding.Address.setText(address.toString())
        binding.Phone.setText(phone.toString())
    }
    private fun updateUserData() {
        var updatename=binding.Name.text.toString()
        var updateEmail=binding.Email.text.toString()
        var updateAddress=binding.Address.toString()
        var updatePhone=binding.Phone.text.toString()
        val currentUserUid=auth.currentUser?.uid
        if(currentUserUid!=null) {
            val userReference = adminReference.child(currentUserUid)
            userReference.child("name").setValue(updatename)
            userReference.child("address").setValue(updateAddress)
            userReference.child("phone").setValue(updatePhone)
            userReference.child("email").setValue(updateEmail)
            Toast.makeText(this, "sucessfully updated", Toast.LENGTH_SHORT).show()
            auth.currentUser?.updateEmail(updateEmail)
        }else{
            Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show()}
    }
}