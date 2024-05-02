package com.fazihaikhlaq.i20_0473


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView

class Book_Session_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_session)

        findViewById<ImageView>(R.id.imageView24).setOnClickListener{
            startActivity(Intent(this,Feedback_Activity::class.java))
        }

        findViewById<ImageView>(R.id.imageView21).setOnClickListener{
            startActivity(Intent(this,Mentors::class.java))
        }

        findViewById<Button>(R.id.button18).setOnClickListener{
            startActivity(Intent(this,Book_Your_Session::class.java))
        }


    }

}