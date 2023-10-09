package com.udemy.firebase_firestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.airbnb.lottie.LottieAnimationView

class MainActivity : AppCompatActivity() {
    lateinit var anim :LottieAnimationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        anim = findViewById(R.id.lottie_layer_name)

        Handler(Looper.getMainLooper()).postDelayed(Runnable {
                 anim.visibility = View.VISIBLE
            anim.playAnimation()

        },2000)

        anim.setOnClickListener(){
            anim.playAnimation()
        }
    }
}