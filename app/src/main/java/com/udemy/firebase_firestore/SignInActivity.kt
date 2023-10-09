package com.udemy.firebase_firestore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.udemy.firebase_firestore.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()
        binding.texttologin.setOnClickListener(){
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }

        binding.btnSign.setOnClickListener(){
            val email = binding.SignEmail.text.toString()
            val password = binding.SignPass.text.toString()
            val ConfirmPass = binding.SignConf.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty() && ConfirmPass.isNotEmpty()){
                if (password == ConfirmPass){
                    firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
                        if (it.isSuccessful){
                            val intent = Intent(this,LoginActivity::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this@SignInActivity,it.exception.toString(),Toast.LENGTH_LONG).show()
                        }
                    }
                }else{
                    Toast.makeText(this@SignInActivity,"$password and $ConfirmPass is not same",Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this@SignInActivity,"Null field not allowed",Toast.LENGTH_LONG).show()
            }
        }

    }
}