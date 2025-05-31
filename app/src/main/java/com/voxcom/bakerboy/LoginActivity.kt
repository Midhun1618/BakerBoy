package com.voxcom.bakerboy

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {
    lateinit var phone: EditText
    lateinit var password: EditText
    lateinit var login: Button
    lateinit var signup: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        phone = findViewById(R.id.phone_ip)
        password = findViewById(R.id.password_id)
        login = findViewById(R.id.login_button)
        signup = findViewById(R.id.signup_text)


        login.setOnClickListener {
            val ph = phone.text.toString()
            val pass = password.text.toString()

            if (ph.isNotEmpty() && pass.isNotEmpty()) {
                if (ph == "123" && pass == "456") {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }
}