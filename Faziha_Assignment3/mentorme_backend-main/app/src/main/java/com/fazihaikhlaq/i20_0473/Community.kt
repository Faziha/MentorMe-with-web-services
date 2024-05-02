package com.fazihaikhlaq.i20_0473

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Community : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_community)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        findViewById<ImageView>(R.id. imageView72).setOnClickListener{
            startActivity(Intent(this,Photo::class.java))
        }
        findViewById<ImageView>(R.id. imageView103).setOnClickListener{
            startActivity(Intent(this,Video_call::class.java))
        }
        findViewById<ImageView>(R.id. imageView97).setOnClickListener{
            startActivity(Intent(this,Call_Duration::class.java))
        }
    }
}