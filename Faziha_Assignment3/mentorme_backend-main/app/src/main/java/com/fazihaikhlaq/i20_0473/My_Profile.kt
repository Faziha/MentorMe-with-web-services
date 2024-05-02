package com.fazihaikhlaq.i20_0473

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class My_Profile : AppCompatActivity() {

    private lateinit var textViewName: TextView;
    private lateinit var textViewCity: TextView;

    private lateinit var sharedPreferences: SharedPreferences
    private val PREF_ID_KEY = "user_id"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_my_profile)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        findViewById<ImageView>(R.id.imageView110).setOnClickListener{
            startActivity(Intent(this,Booked_Sessions::class.java))
        }

        findViewById<ImageView>(R.id.imageView117).setOnClickListener{
            startActivity(Intent(this,Edit_Prof::class.java))
        }

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        textViewName = findViewById(R.id.textView92)
        textViewCity = findViewById(R.id.textView93)

        // Retrieve user ID from SharedPreferences
        val userId = sharedPreferences.getInt(PREF_ID_KEY, -1) // Default value -1 if ID not found

        if (userId != -1) {
            Toast.makeText(this, "User ID :"+userId.toString(), Toast.LENGTH_SHORT).show()
            fetchDataFromServer(userId)
        } else {
            Toast.makeText(this, "User ID not found in SharedPreferences", Toast.LENGTH_SHORT).show()
        }




    }

    private fun fetchDataFromServer(userId: Int) {
        val queue = Volley.newRequestQueue(this)
        val url = "http://172.27.160.1/mentorme/getUserData.php"

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                val status = response.getInt("Status")
                if (status == 1) {
                    val users = response.getJSONArray("users")

                    for (i in 0 until users.length()) {
                        val user = users.getJSONObject(i)

                        val id = user.getInt("id")
                        if (id == userId) {
                            val name = user.getString("name")
                            val city = user.getString("city")
                            // Retrieve other user data as needed

                            // Set the retrieved data on the screen
                            textViewName.text = name
                            textViewCity.text = city

                            // Break the loop once the user is found
                            break
                        }
                    }
                } else {
                    Log.e("FetchData", "No data found")
                }
            },
            { error ->
                Log.e("FetchData", "Error: ${error.message}")
            }
        )

        queue.add(jsonObjectRequest)
    }

}