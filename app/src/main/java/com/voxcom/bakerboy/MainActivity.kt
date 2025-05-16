package com.voxcom.bakerboy

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.TextView
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

    lateinit var muffinCounter: TextView
    lateinit var cupcakeCounter: TextView
    lateinit var donutCounter: TextView
    lateinit var macaronCounter: TextView
    lateinit var bananabreadCounter: TextView
    lateinit var cinnamonrollCounter: TextView
    lateinit var pastryCounter: TextView
    lateinit var redvelvetCounter: TextView
    lateinit var cheesecakeCounter: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val data = getSharedPreferences("live_data", MODE_PRIVATE)
        val edit = data.edit()
        edit.putInt("muffin",0)
        edit.putInt("cupcake",0)
        edit.putInt("donut",0)
        edit.putInt("cinnamonroll",0)
        edit.putInt("bananabread",0)
        edit.putInt("macaron",0)
        edit.putInt("pastry",0)
        edit.putInt("redvelvet",0)
        edit.putInt("cheesecake",0)
        edit.apply()

        muffin = findViewById(R.id.muffin)
        cupcake = findViewById(R.id.cupcake)
        donut = findViewById(R.id.donut)
        cinnamonroll = findViewById(R.id.cinnamonroll)
        bananabread = findViewById(R.id.bananabread)
        macaron = findViewById(R.id.macaron)
        pastry = findViewById(R.id.pastry)
        redvelvet = findViewById(R.id.redvelvet)
        cheesecake = findViewById(R.id.cheesecake)

        muffinCounter = findViewById(R.id.item01Counter)
        cupcakeCounter = findViewById(R.id.item02Counter)
        donutCounter = findViewById(R.id.item03Counter)
        cinnamonrollCounter = findViewById(R.id.item04Counter)
        bananabreadCounter = findViewById(R.id.item05Counter)
        macaronCounter = findViewById(R.id.item06Counter)
        pastryCounter = findViewById(R.id.item07Counter)
        redvelvetCounter = findViewById(R.id.item08Counter)
        cheesecakeCounter = findViewById(R.id.item09Counter)

        muffinCounter.text = data.getInt("muffin",0).toString()
        cupcakeCounter.text = data.getInt("cupcake",0).toString()
        donutCounter.text = data.getInt("donut",0).toString()
        cinnamonrollCounter.text = data.getInt("cinnamonroll",0).toString()
        bananabreadCounter.text = data.getInt("bananabread",0).toString()
        macaronCounter.text = data.getInt("macaron",0).toString()
        pastryCounter.text = data.getInt("pastry",0).toString()
        redvelvetCounter.text = data.getInt("redvelvet",0).toString()
        cheesecakeCounter.text = data.getInt("cheesecake",0).toString()







        }
    }

}