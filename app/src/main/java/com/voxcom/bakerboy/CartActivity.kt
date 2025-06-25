package com.voxcom.bakerboy

import android.net.Uri
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
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
            val upiId = "midhumidhun342-2@okicic"
            val name = "Midhun"
            val note = "BakerBoy Order Payment"

            payUsingUpi(amount, upiId, name, note)
        }
    }
    private fun payUsingUpi(amount: String, upiId: String, name: String, note: String) {
        val uri = Uri.parse("upi://pay").buildUpon()
            .appendQueryParameter("pa", upiId)
            .appendQueryParameter("pn", name)
            .appendQueryParameter("tn", note)
            .appendQueryParameter("am", amount)
            .appendQueryParameter("cu", "INR")
            .build()

        val upiPayIntent = Intent(Intent.ACTION_VIEW)
        upiPayIntent.data = uri

        upiPayIntent.setPackage("com.google.android.apps.nbu.paisa.user")

        if (upiPayIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(upiPayIntent, UPI_PAYMENT_REQUEST_CODE)
        } else {
            Toast.makeText(this, "Google Pay not found. Please install it.", Toast.LENGTH_SHORT).show()
        }
    }

}