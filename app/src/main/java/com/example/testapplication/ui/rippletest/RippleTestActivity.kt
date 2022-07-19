package com.example.testapplication.ui.rippletest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import com.example.testapplication.R
import com.example.testapplication.databinding.ActivityRippleTestBinding

class RippleTestActivity : AppCompatActivity() {

    lateinit var rippleBinding : ActivityRippleTestBinding

    lateinit var zoom : Animation
    lateinit var zoomout : Animation

    var flag = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ripple_test)
        rippleBinding = DataBindingUtil.setContentView(this, R.layout.activity_ripple_test)

        zoom = AnimationUtils.loadAnimation(applicationContext, R.anim.zoom)
        zoomout = AnimationUtils.loadAnimation(applicationContext, R.anim.zoomout)

        rippleBinding.image.setOnClickListener{
            if(flag){
                rippleBinding.image.startAnimation(zoom)
                flag = false
            }
            else{
                rippleBinding.image.startAnimation(zoomout)
                flag = true
            }
        }



    }
}