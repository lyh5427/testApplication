package com.example.testapplication.ui.motionlayouttest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import com.example.testapplication.R
import com.example.testapplication.databinding.ActivityMotionTestBinding

class MotionTestActivity : AppCompatActivity() {
    lateinit var motionBinding : ActivityMotionTestBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND,WindowManager.LayoutParams.FLAG_BLUR_BEHIND)
        motionBinding = DataBindingUtil.setContentView(this, R.layout.activity_motion_test)
        motionBinding.lifecycleOwner = this

        supportFragmentManager.beginTransaction().add(R.id.fragmentContainerView,MotionFragment()).commit()


    }
}