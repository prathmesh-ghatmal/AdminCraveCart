package com.example.admincravecart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.admincravecart.adapter.AllItemAdapter
import com.example.admincravecart.databinding.ActivityAllItemsBinding

class AllItemsActivity : AppCompatActivity() {
    private val binding:ActivityAllItemsBinding by lazy { ActivityAllItemsBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val Itemfoodname= listOf("burger","pizza","momo","mocha","sandwich","gg")
        val Itemprice= listOf("Rs25","Rs85","Rs95","Rs125","Rs15","Rs75")
        val Itemimage= listOf(
            R.drawable.menu1,
            R.drawable.menu2,
            R.drawable.menu3,
            R.drawable.menu4,
            R.drawable.menu5,
            R.drawable.menu5
        )
        val adapter=AllItemAdapter(ArrayList(Itemfoodname),ArrayList(Itemprice),ArrayList(Itemimage))
        binding.MenuRecyclerView.layoutManager= LinearLayoutManager(this)
        binding.MenuRecyclerView.adapter=adapter
        binding.AllItemBackButton.setOnClickListener { finish() }

    }
}