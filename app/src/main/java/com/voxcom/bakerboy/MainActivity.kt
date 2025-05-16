package com.voxcom.bakerboy

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var muffin: CardView
    lateinit var cupcake: CardView
    lateinit var donut: CardView
    lateinit var macaron: CardView
    lateinit var bananabread: CardView
    lateinit var cinnamonroll: CardView
    lateinit var pastry: CardView
    lateinit var redvelvet: CardView
    lateinit var cheesecake: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        muffin = findViewById(R.id.muffin)
        cupcake = findViewById(R.id.cupcake)
        donut = findViewById(R.id.donut)
        cinnamonroll = findViewById(R.id.cinnamonroll)
        bananabread = findViewById(R.id.bananabread)
        macaron = findViewById(R.id.macaron)
        pastry = findViewById(R.id.pastry)
        redvelvet = findViewById(R.id.redvelvet)
        cheesecake = findViewById(R.id.cheesecake)

    }
}