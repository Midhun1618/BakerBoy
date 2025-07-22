package com.voxcom.bakerboy

import android.content.ActivityNotFoundException
import android.net.Uri
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CartActivity : AppCompatActivity() {
    companion object {
        private const val UPI_PAYMENT_REQUEST_CODE = 100
    }
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
        payNow.setOnClickListener {
            val amount = itemList.sumOf { it.count * it.price }.toString()
            makeGooglePayPayment(amount)
        }
    }

    private fun makeGooglePayPayment(amount : String) {
        val upiId = "midhumidhun342-2@okicici"
        val name = "Midhun"
        val amount = amount
        val currency = "INR"
        val transactionNote = "BakerBoy Order Payment"
        val transactionId = "TXN" + System.currentTimeMillis()

        val uri = Uri.parse(
            "upi://pay?pa=$upiId&pn=$name&mc=&tid=$transactionId&tr=$transactionId&tn=$transactionNote&am=$amount&cu=$currency"
        )

        val intent = Intent(Intent.ACTION_VIEW, uri)
        intent.setPackage("com.google.android.apps.nbu.paisa.user")
        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, "Google Pay not installed", Toast.LENGTH_SHORT).show()
        }
    }


}