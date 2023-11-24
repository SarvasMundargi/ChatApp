package com.example.chatapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.chatapplication.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth=FirebaseAuth.getInstance()

        binding.tvSignup.setOnClickListener {
            startActivity(Intent(this,Signup::class.java))
            finish()
        }

        binding.btnLogin.setOnClickListener {
            login()
        }
    }

    private fun login() {
        val email=binding.etEmail.text.toString()
        val password=binding.etPassword.text.toString()

        if(email.isBlank() || password.isBlank()){
            Toast.makeText(this,"Please fill all the Credentials", Toast.LENGTH_SHORT).show()
        }

        else{
            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this){
                if(it.isSuccessful){
                    Toast.makeText(this,"Logged In", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this,MainActivity::class.java))
                    finish()
                }
                else{
                    Toast.makeText(this,"Not proper credentials", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


}