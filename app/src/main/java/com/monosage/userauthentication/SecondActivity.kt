package com.monosage.userauthentication

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    private lateinit var usernameTextView: TextView
    private lateinit var emailTextView:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        usernameTextView=findViewById(R.id.userName)
        emailTextView =findViewById(R.id.email)

        val intent :Intent = this.intent
        var username=intent.getStringExtra("User-Name")
        var email=intent.getStringExtra("E-mail")
        usernameTextView.text="welcome $username!"
        emailTextView.text="Email $email"



    }
}