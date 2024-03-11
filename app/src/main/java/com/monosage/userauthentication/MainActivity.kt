package com.monosage.userauthentication

import android.os.Bundle
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {
    private lateinit var frameLayout: FrameLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        frameLayout=findViewById(R.id.fragmentContainer)

        val signUpFragment=SignUpFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer,signUpFragment)
            .commit()
        }
}
