package com.example.demoapp.ui.Login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.demoapp.databinding.ActivityLogin2Binding
import com.example.demoapp.ui.Home.HomeActivity
import com.example.text_style.textViewStyle


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLogin2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_login2)

        binding = ActivityLogin2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.LoginButton.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            if (binding.editTextTextEmailAddress2.text.toString() == "VAISAKH" && binding.inputEmail.text.toString() == "123456") {


            }


        }

    }
}