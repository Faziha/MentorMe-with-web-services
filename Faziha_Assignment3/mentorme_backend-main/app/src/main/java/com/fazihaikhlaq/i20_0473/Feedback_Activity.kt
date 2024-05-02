package com.fazihaikhlaq.i20_0473

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class Feedback_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)

        findViewById<ImageView>(R.id.imageView21).setOnClickListener{
            startActivity(Intent(this,Book_Session_Activity::class.java))
        }

    }

}