package com.fazihaikhlaq.i20_0473


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.Spinner

class Search_Results_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_results)

        val spinner2 = findViewById<Spinner>(R.id.my_spinner2)

        spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                // Do something with the selected item
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle no selection
            }
        }

        findViewById<ImageView>(R.id. imageView20).setOnClickListener{
            startActivity(Intent(this,SearchActivity::class.java))
        }


        findViewById<ImageView>(R.id.chat).setOnClickListener{
            startActivity(Intent(this,Chats::class.java))
        }
        findViewById<ImageView>(R.id.prof).setOnClickListener{
            startActivity(Intent(this,My_Profile::class.java))
        }
    }
}
