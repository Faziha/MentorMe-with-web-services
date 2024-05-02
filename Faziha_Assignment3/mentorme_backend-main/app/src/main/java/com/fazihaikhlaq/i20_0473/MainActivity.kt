package com.fazihaikhlaq.i20_0473

// Import the necessary libraries
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import org.json.JSONObject


class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var sharedPreferences: SharedPreferences
    private val PREF_ID_KEY = "user_id"


    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
//        val currentUser = auth.currentUser
//        if (currentUser != null) {
//            startActivity(Intent(this,Mentors::class.java))
//        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        auth = Firebase.auth


        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        var signUptext: TextView
        signUptext = findViewById(R.id.signUp)
        var str = "Sign up"
        val mSpannableString = SpannableString(str)

        mSpannableString.setSpan(UnderlineSpan(), 0, mSpannableString.length, 0)

        signUptext.text = mSpannableString

        signUptext.setOnClickListener{
            startActivity(Intent(this,SignUp::class.java))
        }
        var f_pass = findViewById<TextView>(R.id.textView8)
        f_pass.setOnClickListener{
            startActivity(Intent(this,forgot_pass::class.java))
        }

        findViewById<Button>(R.id.signup_btn).setOnClickListener {
            // get email and password and verify them

            var email: String = findViewById<EditText>(R.id.editTextTextEmailAddress2).text.toString()
            var password: String = findViewById<EditText>(R.id.editTextTextPassword).text.toString()



            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Make API call to PHP endpoint
            val url = "http://172.27.160.1/mentorme/signin.php"

            val jsonObject = JSONObject()
            jsonObject.put("email", email)
            jsonObject.put("password", password)

            val request = JsonObjectRequest(
                Request.Method.POST, url, jsonObject,
                { response ->
                    val status = response.getInt("Status")
                    if (status == 1) {
                        val userId = response.getInt("UserID")
                        saveUserId(userId)
                        Toast.makeText(this, "Signin successful", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, Mentors::class.java))
                        finish()
                    } else {
                        val message = response.getString("Message")
                        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                    }
                },
                { error ->
                    Toast.makeText(this, "Error occurred: ${error.message}", Toast.LENGTH_SHORT).show()
                }
            )

            Volley.newRequestQueue(this).add(request)
        }
    }

    private fun saveUserId(userId: Int) {
        val editor = sharedPreferences.edit()
        editor.putInt(PREF_ID_KEY, userId)
        editor.apply()
    }

}