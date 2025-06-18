package com.voxcom.bakerboy

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CartActivity : AppCompatActivity() {
    lateinit var backButton: ImageButton
    lateinit var amountTotal: TextView
    lateinit var payNow: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cart)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        backButton = findViewById(R.id.backButton)

        amountTotal =findViewById(R.id.amount_total)
        payNow = findViewById(R.id.paybutton)

        val itemList = intent.getSerializableExtra("selectedItems") as ArrayList<ItemData>
        val totalPrice = itemList.sumOf { it.count * it.price }

        amountTotal.text = "₹$totalPrice"
        payNow.isEnabled = false

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CartAdapter(itemList)

        if(totalPrice>0){
            payNow.isEnabled = true
        }
        backButton.setOnClickListener {
            finish()
        }
    }
}