package com.example.chatapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.chatapplication.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Signup : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth=FirebaseAuth.getInstance()

        binding.btnSignup.setOnClickListener {
            signup()
        }

        binding.tvLogin.setOnClickListener {
            startActivity(Intent(this,Login::class.java))
        }
    }

    private fun signup() {
        val email=binding.etEmail.text.toString()
        val password=binding.etPassword.text.toString()
        val name=binding.etName.text.toString()

        if(email.isBlank() || password.isBlank()){
            Toast.makeText(this,"Please fill all the Credentials", Toast.LENGTH_SHORT).show()
        }
        else{
            auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
                if(it.isSuccessful){
                    addUsertoDB(name,email,auth.currentUser!!.uid)
                    Toast.makeText(this,"Logged In",Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this,MainActivity::class.java))
                    finish()
                }
                else{
                    Toast.makeText(this,"Error Connecting User",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun addUsertoDB(name: String, email: String, uid: String) {
        mDbRef=FirebaseDatabase.getInstance().getReference()
        mDbRef.child("users").child(uid).setValue(User(name,email,uid))
    }
}