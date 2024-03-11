package com.monosage.userauthentication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.text.method.PasswordTransformationMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class SignUpFragment : Fragment() {
    private lateinit var usernameED: EditText
    private lateinit var emailED: EditText
    private lateinit var phonenumED: EditText
    private lateinit var passwordED: EditText
    private lateinit var cfPasswordED: EditText
    private lateinit var createbtnBTN: Button
    private lateinit var tViewTV: TextView
    private lateinit var showPs: ImageView
    private lateinit var showOf:ImageView

    private var isPasswordVisible=false


    private lateinit var username: String
    private lateinit var email: String
    private lateinit var phonenum: String
    private lateinit var password: String
    private lateinit var cfPassword: String

    lateinit var finalEmail: String
    lateinit var finalMobileNumber: String
    lateinit var finalPassword: String
    lateinit var passwordToBeStored: String

    private lateinit var sharedPreferences: SharedPreferences
    private val pref_name = "Myprefs"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sign_up, container, false)
        emailED = view.findViewById(R.id.emailEditText)
        usernameED = view.findViewById(R.id.username)
        phonenumED = view.findViewById(R.id.phoneNum)
        passwordED = view.findViewById(R.id.password)
        cfPasswordED = view.findViewById(R.id.etPassword)
        createbtnBTN = view.findViewById(R.id.Btn1)
        tViewTV = view.findViewById(R.id.tv4)
        showPs=view.findViewById(R.id.eyeIcon)
        showOf=view.findViewById(R.id.eyeEon)

        showPs.setOnClickListener {
            togglePasswordVisibility()
        }

        showOf.setOnClickListener {
            togglePasswordV()
        }
        createbtnBTN.setOnClickListener {

            username = usernameED.text.toString().trim()
            email = emailED.text.toString().trim()
            password = passwordED.text.toString().trim()
            cfPassword = cfPasswordED.text.toString().trim()
            phonenum = phonenumED.text.toString().trim()


            val isValid = checkAllFields()
            if (isValid) {

                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, LoginFragment()).commit()

                sharedPreferences =
                    requireActivity().getSharedPreferences(pref_name, Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("username", username)
                editor.putString("Email", finalEmail)
                editor.putString("Password", passwordToBeStored)
                editor.apply()

            } else {
                Toast.makeText(requireActivity(), "Sign up Failed, Please rectify your details", Toast.LENGTH_SHORT).show()
            }
        }
        tViewTV.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, LoginFragment()).commit()

        }


        return view
    }
    private fun togglePasswordVisibility(){
        isPasswordVisible =!isPasswordVisible
        val drawable =if(isPasswordVisible) {
            R.drawable.eye
        }else{
            R.drawable.eye_c
        }
        showPs.setImageResource(drawable)
        passwordED.transformationMethod= if(isPasswordVisible)null else PasswordTransformationMethod.getInstance()
    }
    private fun togglePasswordV(){
        isPasswordVisible =!isPasswordVisible
        val drawable =if(isPasswordVisible) {
            R.drawable.eye
        }else{
            R.drawable.eye_c
        }
        showOf.setImageResource(drawable)
        cfPasswordED.transformationMethod= if(isPasswordVisible)null else PasswordTransformationMethod.getInstance()
    }

    private fun checkAllFields(): Boolean {
        if (username == "") {
            usernameED.error = "This field is required"
            return false
        }

        //For email
        val emailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z0-9,-]+\\.[a-zA-Z]{2,4}"
        if (email == "") {
            emailED.error = "This field is required"
            return false
        } else if (email.matches(Regex(emailPattern))) {
            finalEmail = email
        } else {
            emailED.error = "Email is invalid"
            return false
        }

        //For mobile no
        val mobilePattern = "[0-9]{10}"
        if (phonenum == "") {
            phonenumED.error = "This field is required"
            return false
        } else if (phonenum.matches(Regex(mobilePattern))) {
            finalMobileNumber = phonenum
        } else {
            phonenumED.error="Mobile no is invalid"
            return false
        }


        if (password == "") {
            Toast.makeText(requireActivity(),"This field is required",Toast.LENGTH_SHORT).show()
            return false
        } else if (password.length >= 6) {
            finalPassword = password
        } else {
            passwordED.error="Password is greater than 6 characters"
            return false
        }


        if (cfPassword == "") {

            Toast.makeText(requireActivity(),"This field is required",Toast.LENGTH_SHORT).show()
            return false
        } else if (cfPassword == finalPassword) {
            passwordToBeStored = password
        } else {
            cfPasswordED.error = "Password and confirm password are not same"
            return false
        }
        return true


    }

}