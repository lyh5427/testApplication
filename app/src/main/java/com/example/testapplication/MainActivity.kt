package com.example.testapplication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.dynamiclinks.ktx.dynamicLinks
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val shareBtn = findViewById<Button>(R.id.sharebtn)
        val viewbind = findViewById<Button>(R.id.viewbinding)

        shareBtn.setOnClickListener{
            intent = Intent(this, ShareTest::class.java)
            startActivity(intent)
        }

        viewbind.setOnClickListener {
            intent = Intent(this, ViewBindingActivity::class.java)
            startActivity(intent)
        }



    }
}