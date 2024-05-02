package com.fazihaikhlaq.i20_0473


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.widget.Button
import android.widget.TextView

class forgot_pass : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_pass)
        var Logintext: TextView
        Logintext = findViewById(R.id.textView15)
        var str = "Login"
        val mSpannableString = SpannableString(str)

        mSpannableString.setSpan(UnderlineSpan(), 0, mSpannableString.length, 0)

        Logintext.text = mSpannableString

        Logintext.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        var send_email = findViewById<Button>(R.id.button12)
        send_email.setOnClickListener {
            startActivity(Intent(this,verification_form::class.java))
        }
    }
}