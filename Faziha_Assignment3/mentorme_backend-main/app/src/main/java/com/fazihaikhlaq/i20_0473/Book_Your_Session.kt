package com.fazihaikhlaq.i20_0473

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Book_Your_Session : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_book_your_session)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        findViewById<ImageView>(R.id.imageView52).setOnClickListener{
            startActivity(Intent(this,Call_Duration::class.java))
        }

        findViewById<ImageView>(R.id.imageView53).setOnClickListener{
            startActivity(Intent(this,Video_call::class.java))
        }
        findViewById<Button>(R.id.button26).setOnClickListener{
            startActivity(Intent(this,Booked_Sessions::class.java))
        }
    }
}