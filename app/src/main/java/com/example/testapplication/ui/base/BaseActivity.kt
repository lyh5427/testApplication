package com.example.testapplication.ui.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testapplication.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }
}