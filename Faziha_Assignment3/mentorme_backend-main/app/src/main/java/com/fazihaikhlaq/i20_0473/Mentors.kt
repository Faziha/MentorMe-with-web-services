package com.fazihaikhlaq.i20_0473


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

@Suppress("DEPRECATION")
class Mentors : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mentors)



        findViewById<LinearLayout>(R.id.search_btn).setOnClickListener{
            startActivity(Intent(this,SearchActivity::class.java))
        }

        findViewById<CardView>(R.id.cooper_card1).setOnClickListener{
            startActivity(Intent(this,Book_Session_Activity::class.java))
        }

        findViewById<ImageView>(R.id.postImage).setOnClickListener{
            //startActivity(Intent(this,Add_New_Mentor::class.java))
        }

        findViewById<ImageView>(R.id.postImage).setOnClickListener{
            startActivity(Intent(this,Add_New_Mentor::class.java))
        }
        findViewById<ImageView>(R.id.chat).setOnClickListener{
            startActivity(Intent(this,Chats::class.java))
        }

        findViewById<ImageView>(R.id.imageView7).setOnClickListener{
            startActivity(Intent(this,Notifications::class.java))
        }
        findViewById<ImageView>(R.id.prof).setOnClickListener{
            startActivity(Intent(this,My_Profile::class.java))
        }

    }
}