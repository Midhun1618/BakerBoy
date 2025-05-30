package com.voxcom.bakerboy

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
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
    lateinit var cartCounter: TextView


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
        cartCounter = findViewById(R.id.cartCounter)

        muffinCounter.text = data.getInt("muffin",0).toString()
        cupcakeCounter.text = data.getInt("cupcake",0).toString()
        donutCounter.text = data.getInt("donut",0).toString()
        cinnamonrollCounter.text = data.getInt("cinnamonroll",0).toString()
        bananabreadCounter.text = data.getInt("bananabread",0).toString()
        macaronCounter.text = data.getInt("macaron",0).toString()
        pastryCounter.text = data.getInt("pastry",0).toString()
        redvelvetCounter.text = data.getInt("redvelvet",0).toString()
        cheesecakeCounter.text = data.getInt("cheesecake",0).toString()
        cartCounter.text = data.getInt("cart",0).toString()


        muffin.setOnClickListener {
            var count = data.getInt("muffin",0)
            var cartcount = data.getInt("cart",0)
            if(count==0){
                edit.putInt("muffin",count+1)
                edit.putInt("cart",cartcount+1)
                cartCounter.visibility=View.VISIBLE
                muffinCounter.visibility=View.VISIBLE
                muffin.setBackgroundTintList(getColorStateList(R.color.blue1))
            }
            else{
                edit.putInt("muffin",0)
                muffinCounter.visibility=View.INVISIBLE
                muffin.setBackgroundTintList(getColorStateList(R.color.white))
            }
            edit.apply()
            count = data.getInt("muffin",0)
            muffinCounter.text = count.toString()
        }
        cupcake.setOnClickListener {
            var count = data.getInt("cupcake",0)
            var cartcount = data.getInt("cart",0)
            if(count==0){
                edit.putInt("cupcake",count+1)
                edit.putInt("cart",cartcount+1)
                cartCounter.visibility=View.VISIBLE
                cupcakeCounter.visibility=View.VISIBLE
                cupcake.setBackgroundTintList(getColorStateList(R.color.blue1))
            }
            else{
                edit.putInt("cupcake",0)
                cupcakeCounter.visibility=View.INVISIBLE
                cupcake.setBackgroundTintList(getColorStateList(R.color.white))
            }
            edit.apply()
            count = data.getInt("cupcake",0)
            cupcakeCounter.text = count.toString()
        }
        donut.setOnClickListener {
            var count = data.getInt("donut",0)
            var cartcount = data.getInt("cart",0)
            if(count==0){
                edit.putInt("donut",count+1)
                edit.putInt("cart",cartcount+1)
                cartCounter.visibility=View.VISIBLE
                donutCounter.visibility=View.VISIBLE
                donut.setBackgroundTintList(getColorStateList(R.color.blue1))
            }
            else{
                edit.putInt("donut",0)
                donutCounter.visibility=View.INVISIBLE
                donut.setBackgroundTintList(getColorStateList(R.color.white))
            }
            edit.apply()
            count = data.getInt("donut",0)
            donutCounter.text = count.toString()
        }
        cinnamonroll.setOnClickListener {
            var count = data.getInt("cinnamonroll",0)
            var cartcount = data.getInt("cart",0)
            if(count==0){
                edit.putInt("cinnamonroll",count+1)
                edit.putInt("cart",cartcount+1)
                cartCounter.visibility=View.VISIBLE
                cinnamonrollCounter.visibility=View.VISIBLE
                cinnamonroll.setBackgroundTintList(getColorStateList(R.color.blue1))
            }
            else{
                edit.putInt("cinnamonroll",0)
                cinnamonrollCounter.visibility=View.INVISIBLE
                cinnamonroll.setBackgroundTintList(getColorStateList(R.color.white))
            }
            edit.apply()
            count = data.getInt("cinnamonroll",0)
            cinnamonrollCounter.text = count.toString()
        }

        bananabread.setOnClickListener {
            var count = data.getInt("cupcake",0)
            var cartcount = data.getInt("cart",0)
            if(count==0){
                edit.putInt("cupcake",count+1)
                edit.putInt("cart",cartcount+1)
                cartCounter.visibility=View.VISIBLE
                cupcakeCounter.visibility=View.VISIBLE
                cupcake.setBackgroundTintList(getColorStateList(R.color.blue1))
            }
            else{
                edit.putInt("cupcake",0)
                cupcakeCounter.visibility=View.INVISIBLE
                cupcake.setBackgroundTintList(getColorStateList(R.color.white))
            }
            edit.apply()
            count = data.getInt("cupcake",0)
            cupcakeCounter.text = count.toString()
        }
        macaron.setOnClickListener {
            var count = data.getInt("donut",0)
            var cartcount = data.getInt("cart",0)
            if(count==0){
                edit.putInt("donut",count+1)
                edit.putInt("cart",cartcount+1)
                cartCounter.visibility=View.VISIBLE
                donutCounter.visibility=View.VISIBLE
                donut.setBackgroundTintList(getColorStateList(R.color.blue1))
            }
            else{
                edit.putInt("donut",0)
                donutCounter.visibility=View.INVISIBLE
                donut.setBackgroundTintList(getColorStateList(R.color.white))
            }
            edit.apply()
            count = data.getInt("donut",0)
            donutCounter.text = count.toString()
        }
        pastry.setOnClickListener {
            var count = data.getInt("pastry",0)
            var cartcount = data.getInt("cart",0)
            if(count==0){
                edit.putInt("pastry",count+1)
                edit.putInt("cart",cartcount+1)
                cartCounter.visibility=View.VISIBLE
                pastryCounter.visibility=View.VISIBLE
                pastry.setBackgroundTintList(getColorStateList(R.color.blue1))
            }
            else{
                edit.putInt("pastry",0)
                pastryCounter.visibility=View.INVISIBLE
                pastry.setBackgroundTintList(getColorStateList(R.color.white))
            }
            edit.apply()
            count = data.getInt("pastry",0)
            pastryCounter.text = count.toString()
        }
        redvelvet.setOnClickListener {
            var count = data.getInt("muffin",0)
            var cartcount = data.getInt("cart",0)
            if(count==0){
                edit.putInt("muffin",count+1)
                edit.putInt("cart",cartcount+1)
                cartCounter.visibility=View.VISIBLE
                redvelvetCounter.visibility=View.VISIBLE
                redvelvet.setBackgroundTintList(getColorStateList(R.color.blue1))
            }
            else{
                edit.putInt("muffin",0)
                redvelvetCounter.visibility=View.INVISIBLE
                redvelvet.setBackgroundTintList(getColorStateList(R.color.white))
            }
            edit.apply()
            count = data.getInt("muffin",0)
            redvelvetCounter.text = count.toString()
        }
    }

}