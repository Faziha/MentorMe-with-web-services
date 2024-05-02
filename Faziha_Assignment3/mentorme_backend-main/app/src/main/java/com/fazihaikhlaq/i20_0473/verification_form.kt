package com.fazihaikhlaq.i20_0473

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class verification_form : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification_form)

        findViewById<ImageView>(R.id.imageView2).setOnClickListener{
            startActivity(Intent(this,Reset_pass::class.java))
        }

        findViewById<Button>(R.id.button).setOnClickListener{
            startActivity(Intent(this,Reset_pass::class.java))
        }
    }
}