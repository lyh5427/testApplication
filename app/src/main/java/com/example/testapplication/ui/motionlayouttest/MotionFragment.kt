package com.example.testapplication.ui.motionlayouttest

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.example.testapplication.R
import com.example.testapplication.databinding.FragmentMotionBinding
import java.lang.Math.abs

class MotionFragment : Fragment() {
    lateinit var motionfBinding : FragmentMotionBinding
    var f = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        motionfBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_motion, container, false)
        motionfBinding.lifecycleOwner=this

        motionfBinding.frameLayout.transitionToEnd()
        motionfBinding.frameLayout.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionStarted(motionLayout: MotionLayout?, startId: Int, endId: Int) {
                Log.d("aaaaaaaaaaaaa", "${motionfBinding.frameLayout.progress} aaaa")
            }

            override fun onTransitionChange(motionLayout: MotionLayout?, startId: Int, endId: Int, progress: Float) {
                if(motionfBinding.frameLayout.progress ==0f){
                    f=true
                }
            }

            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {

                Log.d("ccccccccc", "${motionfBinding.frameLayout.progress} aaaa")
                if(motionfBinding.frameLayout.progress == 1f && f){
                    requireActivity().supportFragmentManager.beginTransaction().remove(MotionFragment()).commit()
                }
            }

            override fun onTransitionTrigger(motionLayout: MotionLayout?, triggerId: Int, positive: Boolean, progress: Float) {

            }
        })

        return motionfBinding.root
    }
}