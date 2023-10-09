package com.udemy.firebase_firestore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.udemy.firebase_firestore.databinding.ActivityLoginBinding
import com.udemy.firebase_firestore.databinding.ActivitySignInBinding


class LoginActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.texttosignin.setOnClickListener(){
            val intent = Intent(this@LoginActivity,SignInActivity::class.java)
            startActivity(intent)
        }
        binding.btnlogin.setOnClickListener(){
            val email = binding.loginEmail.text.toString()
            val passowrd = binding.loginPass.text.toString()

            if (email.isNotEmpty() && passowrd.isNotEmpty()){
                firebaseAuth.createUserWithEmailAndPassword(email,passowrd).addOnCompleteListener {
                    if (it.isSuccessful){
                        val intent = Intent(this@LoginActivity,MainActivity::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this,it.exception.toString(),Toast.LENGTH_LONG).show()
                    }
                }
            }else{
                Toast.makeText(this,"Null field not allowed",Toast.LENGTH_LONG).show()
            }
        }



    }

    override fun onStart() {
        if (firebaseAuth.currentUser != null)
        super.onStart()
        val intent = Intent(this,MainActivity::class.java)
    }
}