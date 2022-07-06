package com.example.testapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.testapplication.databinding.ActivityViewBindingBinding

class ViewBindingActivity : AppCompatActivity() {

    lateinit var viewBindingActivity : ActivityViewBindingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBindingActivity = ActivityViewBindingBinding.inflate(layoutInflater)
        setContentView(viewBindingActivity.root)

        Log.d("root ", " = ${viewBindingActivity.root.toString()}")
    }
}