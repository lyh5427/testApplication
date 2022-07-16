package com.example.testapplication.ui.motionlayouttest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.testapplication.R
import com.example.testapplication.databinding.ActivityMotionTestBinding

class MotionTestActivity : AppCompatActivity() {
    lateinit var motionBinding : ActivityMotionTestBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        motionBinding = DataBindingUtil.setContentView(this, R.layout.activity_motion_test)
        motionBinding.lifecycleOwner = this

        supportFragmentManager.beginTransaction().add(R.id.motionFrame,MotionFragment()).commit()

    }
}