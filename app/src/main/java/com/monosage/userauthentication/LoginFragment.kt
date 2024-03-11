package com.monosage.userauthentication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class LoginFragment : Fragment() {
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var lgnBtn: Button
    private lateinit var singUp:TextView
    private lateinit var showPs:ImageView


    private var isPasswordVisible=false

    private lateinit var sharedPreferences:SharedPreferences
    private val pref_name:String="Myprefs"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_loginfragment, container, false)

        emailEditText = view.findViewById(R.id.emailEditText)
        passwordEditText = view.findViewById(R.id.password)
        lgnBtn = view.findViewById(R.id.login_btn)
        singUp = view.findViewById(R.id.tv5)
        showPs = view.findViewById(R.id.eyeIcon)

        sharedPreferences = requireActivity().getSharedPreferences(pref_name, Context.MODE_PRIVATE)
        val user = sharedPreferences.getString("username", "defaultName")
        val mail = sharedPreferences.getString("Email", "defaultEmail")
        val psWd = sharedPreferences.getString("Password", "defaultPassword")


        showPs.setOnClickListener {
            togglePasswordVisibility()
        }


        lgnBtn.setOnClickListener {
            val email=emailEditText.text.toString().trim()
            val password=passwordEditText.text.toString().trim()

            if(isValidEmail(email)&&isValidPassword(password)){
                if(email==mail && password==psWd){
                    val intent =Intent(requireActivity(),SecondActivity::class.java)
                    intent.putExtra("E-mail",mail)
                    intent.putExtra("User-Name",user)
                    startActivity(intent)
                    requireActivity().finish()
                    Toast.makeText(requireContext(),"LoginSuccessful",Toast.LENGTH_SHORT).show()
                }
                else
                {
                    Toast.makeText(requireActivity(),"Email or password is incorrect",Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(requireContext(),"Invalid email or password",Toast.LENGTH_SHORT).show()
            }
        }

        singUp.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, SignUpFragment()).commit()

        }
        return view
    }
    private fun togglePasswordVisibility(){
        isPasswordVisible =!isPasswordVisible
        val drawable =if(isPasswordVisible) {
            R.drawable.eye_c
        }else{
            R.drawable.eye
        }
        showPs.setImageResource(drawable)
        passwordEditText.transformationMethod= if(isPasswordVisible)null else PasswordTransformationMethod.getInstance()
    }

    private fun isValidEmail(email:String):Boolean{
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    private fun isValidPassword(password:String):Boolean{
        return password.isNotEmpty()
    }
}