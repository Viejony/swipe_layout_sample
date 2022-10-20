package com.co.widetech.swipelayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.co.widetech.swipelayout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val itemList = listOf("One", "Two", "Three")
        binding.list.layoutManager = LinearLayoutManager(this)
        adapter = ItemAdapter(this, itemList)
        binding.list.adapter = adapter
    }
}