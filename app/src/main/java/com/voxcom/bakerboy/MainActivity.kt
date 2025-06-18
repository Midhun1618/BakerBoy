package com.voxcom.bakerboy

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.Serializable

data class ItemData(
    val name: String,
    val count: Int,
    val price: Int
) : Serializable
data class BakeryItem(
    val name: String,
    val cardView: CardView,
    val counterView: TextView
)




class MainActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var cartCounter: TextView
    private lateinit var cartButton: ImageButton
    private val items = mutableListOf<BakeryItem>()

    private val itemPrices = mapOf(
        "muffin" to 29,
        "cupcake" to 25,
        "donut" to 19,
        "cinnamonroll" to 35,
        "bananabread" to 39,
        "macaron" to 49,
        "pastry" to 45,
        "redvelvet" to 55,
        "cheesecake" to 59
    )

    val itemKeys = listOf( "muffin", "cupcake", "donut", "cinnamonroll","bananabread", "macaron", "pastry", "redvelvet", "cheesecake")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        sharedPreferences = getSharedPreferences("live_data", MODE_PRIVATE)
        cartCounter = findViewById(R.id.cartCounter)
        cartButton = findViewById(R.id.cart_button)

        setupItems()
        updateAllCounters()
        setupClickListeners()
        counterClickListeners()

        cartButton.setOnClickListener {
            val data = getSharedPreferences("live_data", MODE_PRIVATE)
            val selectedItems = itemKeys.mapNotNull { key ->
                val count = data.getInt(key, 0)
                val price = itemPrices[key] ?: 0
                if (count > 0) ItemData(key, count, price) else null
            }

            val intent = Intent(this, CartActivity::class.java)
            intent.putExtra("selectedItems", ArrayList(selectedItems))
            startActivity(intent)
        }

    }

    private fun setupItems() {
        val itemIds = listOf(
            Triple("muffin", R.id.muffin, R.id.item01Counter),
            Triple("cupcake", R.id.cupcake, R.id.item02Counter),
            Triple("donut", R.id.donut, R.id.item03Counter),
            Triple("macaron", R.id.macaron, R.id.item04Counter),
            Triple("bananabread", R.id.bananabread, R.id.item05Counter),
            Triple("cinnamonroll", R.id.cinnamonroll, R.id.item06Counter),
            Triple("pastry", R.id.pastry, R.id.item07Counter),
            Triple("redvelvet", R.id.redvelvet, R.id.item08Counter),
            Triple("cheesecake", R.id.cheesecake, R.id.item09Counter)
        )

        itemIds.forEach { (name, cardId, counterId) ->
            val card = findViewById<CardView>(cardId)
            val counter = findViewById<TextView>(counterId)
            items.add(BakeryItem(name, card, counter))
        }
    }

    private fun setupClickListeners() {
        items.forEach { item ->
            item.cardView.setOnClickListener {
                toggleItem(item)
            }
        }
    }
    private fun counterClickListeners() {
        items.forEach { item ->
            item.counterView.setOnClickListener {
                countUp(item)
            }
        }
    }
    private fun countUp(item: BakeryItem) {
        val currentCount = sharedPreferences.getInt(item.name, 0)
        val newCount = currentCount+1

        sharedPreferences.edit().putInt(item.name, newCount).apply()

        item.counterView.text = newCount.toString()

        updateCartCounter()
    }

    private fun toggleItem(item: BakeryItem) {
        val currentCount = sharedPreferences.getInt(item.name, 0)
        val newCount = if (currentCount == 0) 1 else 0

        sharedPreferences.edit().putInt(item.name, newCount).apply()

        item.counterView.visibility = if (newCount == 0) View.INVISIBLE else View.VISIBLE
        item.cardView.setBackgroundTintList(getColorStateList(
            if (newCount == 0) R.color.white else R.color.blue1
        ))
        item.counterView.text = newCount.toString()

        updateCartCounter()
    }

    private fun updateAllCounters() {
        items.forEach { item ->
            val count = sharedPreferences.getInt(item.name, 0)
            item.counterView.visibility = if (count == 0) View.INVISIBLE else View.VISIBLE
            item.counterView.text = count.toString()
            item.cardView.setBackgroundTintList(getColorStateList(
                if (count == 0) R.color.white else R.color.blue1
            ))
        }
        updateCartCounter()
    }

    private fun updateCartCounter() {
        val total = items.sumOf { sharedPreferences.getInt(it.name, 0) }
        sharedPreferences.edit().putInt("cart", total).apply()
        cartCounter.text = total.toString()
        cartCounter.visibility = if (total == 0) View.INVISIBLE else View.VISIBLE
    }
}