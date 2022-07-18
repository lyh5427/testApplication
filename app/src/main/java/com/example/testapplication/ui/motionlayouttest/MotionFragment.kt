package com.example.testapplication.ui.motionlayouttest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.databinding.DataBindingUtil
import com.example.testapplication.R
import com.example.testapplication.databinding.FragmentMotionBinding
import java.lang.Math.abs

class MotionFragment : Fragment() {
    lateinit var motionfBinding : FragmentMotionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        motionfBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_motion, container, false)

        motionfBinding.frameLayout.transitionToEnd()
        return motionfBinding.root
    }
    private fun initMotionLayoutEvent(fragmentPlayerBinding: FragmentMotionBinding) {
        fragmentPlayerBinding.frameLayout.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionStarted(motionLayout: MotionLayout?, startId: Int, endId: Int) {}

            override fun onTransitionChange(motionLayout: MotionLayout?, startId: Int, endId: Int, progress: Float) {
                motionfBinding?.let {
                    (activity as MotionTestActivity).also { mainActivity ->
                        // mainActivity 로 치환해서 형변환
                        mainActivity.findViewById<MotionLayout>(R.id.constraintLayout2).progress = abs(progress)
                    }
                }
            }

            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {}

            override fun onTransitionTrigger(motionLayout: MotionLayout?, triggerId: Int, positive: Boolean, progress: Float) {}
        })
    }

}