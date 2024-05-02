package com.fazihaikhlaq.i20_0473


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class SignUp : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var numberEditText: EditText
    private lateinit var passwordEditText: EditText

    private lateinit var sharedPreferences: SharedPreferences
    private val PREF_ID_KEY = "user_id"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up) // Make sure this layout exists in your project

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        nameEditText = findViewById(R.id.editTextText2)
        emailEditText = findViewById(R.id.editTextTextEmailAddress)
        numberEditText = findViewById(R.id.editTextNumber)
        passwordEditText = findViewById(R.id.editTextTextPassword3)
        val countrySpinner: Spinner = findViewById(R.id.my_spinner)
        val citySpinner: Spinner = findViewById(R.id.my_spinner1)
        val signUpButton: Button = findViewById(R.id.signup_btn)

        var Logintext: TextView
        Logintext = findViewById(R.id.signUp)
        var str = "Log in"
        val mSpannableString = SpannableString(str)
        mSpannableString.setSpan(UnderlineSpan(), 0, mSpannableString.length, 0)
        Logintext.text = mSpannableString

        Logintext.setOnClickListener{
            startActivity(Intent(this,MainActivity::class.java))
        }


        val spinner = findViewById<Spinner>(R.id.my_spinner)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
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
//        findViewById<Button>(R.id.signup_btn).setOnClickListener{
//            startActivity(Intent(this,MainActivity::class.java))
//        }


        signUpButton.setOnClickListener {
            val name = nameEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()
            val number = numberEditText.text.toString().trim()
            val country = countrySpinner.selectedItem.toString()
            val city = citySpinner.selectedItem.toString()
            val password = passwordEditText.text.toString().trim()

            // Validate inputs
            if (name.isEmpty() || email.isEmpty() || number.isEmpty() || country.isEmpty() || city.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Make API call to PHP endpoint
            val url = "http://172.27.160.1/mentorme/signup.php"

            val jsonObject = JSONObject()
            jsonObject.put("name", name)
            jsonObject.put("email", email)
            jsonObject.put("number", number)
            jsonObject.put("country", country)
            jsonObject.put("city", city)
            jsonObject.put("password", password)

            Toast.makeText(this, "json obj: "+jsonObject.toString(), Toast.LENGTH_LONG).show()

            val request = JsonObjectRequest(
                Request.Method.POST, url, jsonObject,
                { response ->
                    val status = response.getInt("Status")
                    if (status == 1) {
                        val id = response.getInt("id")
                        sharedPreferences.edit().putInt(PREF_ID_KEY, id).apply()


                        Toast.makeText(this, "Signup successful", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    } else {
                        val message = response.getString("Message")
                        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                    }
                },
                { error ->
                    Toast.makeText(this, "Error occurred: ${error.message}", Toast.LENGTH_SHORT).show()
                })

// Add the request to the RequestQueue
            Volley.newRequestQueue(this).add(request)
        }


    }
}
