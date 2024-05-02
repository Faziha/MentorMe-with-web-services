package com.fazihaikhlaq.i20_0473

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class Edit_Prof : AppCompatActivity() {

    private lateinit var textViewName: EditText;
    private lateinit var textViewEmail: EditText;
    private lateinit var textViewNumber: EditText;
    private lateinit var textViewCountry: Spinner;
    private lateinit var textViewCity: Spinner;

    private lateinit var sharedPreferences: SharedPreferences
    private val PREF_ID_KEY = "user_id"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edit_prof)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        textViewName = findViewById(R.id.editTextText)
        textViewEmail = findViewById(R.id.editTextTextEmailAddress4)
        textViewNumber = findViewById(R.id.editTextNumber2)
        textViewCountry = findViewById(R.id.my_spinner)
        textViewCity = findViewById(R.id.my_spinner1)

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

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
                            val number = user.getString("number")
                            val city = user.getString("city")
                            val country = user.getString("country")
                            val email = user.getString("email")

                            // Set the retrieved data on the screen
                            textViewName.setText(name)
                            textViewEmail.setText(email)
                            textViewNumber.setText(number)

                            val countryIndex = getCountryIndex(country)
                            if (countryIndex != -1) {
                                textViewCountry.setSelection(countryIndex)
                            }

                            val cityIndex = getCityIndex(city)
                            if (cityIndex != -1) {
                                textViewCity.setSelection(cityIndex)
                            }

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


    private fun getCountryIndex(country: String): Int {
        val countryArray = resources.getStringArray(R.array.dropdown_options)
        return countryArray.indexOf(country)
    }

    // Function to get the index of the city in the Spinner
    private fun getCityIndex(city: String): Int {
        val cityArray = resources.getStringArray(R.array.city_options)
        return cityArray.indexOf(city)
    }
}