package com.example.testapplication.ui.motionlayouttest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.testapplication.R
import com.example.testapplication.databinding.FragmentMotionBinding

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


}