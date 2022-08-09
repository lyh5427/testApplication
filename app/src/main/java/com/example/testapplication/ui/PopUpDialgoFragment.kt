package com.example.testapplication.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.example.testapplication.R
import com.example.testapplication.databinding.FragmentPopUpDialgoBinding
import com.example.testapplication.databinding.FragmentPopUpDialog2Binding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PopUpDialgoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PopUpDialgoFragment : DialogFragment() {
    // TODO: Rename and change types of parameters
    private var title: String? = null
    private var param2: Int? = null
    lateinit var listener : Dialog2
    lateinit var binding : FragmentPopUpDialgoBinding
    lateinit var binding2 : FragmentPopUpDialog2Binding

    fun addListener( l : Dialog2 ){
        this.listener = l
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            title = it.getString("title")
            param2 = it.getInt(ARG_PARAM2)
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if(param2 == 1){
            binding = FragmentPopUpDialgoBinding.inflate(inflater, container, false)
            dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            binding.textView11.setOnClickListener{

                listener!!.cancel()
                dismiss()
            }
            return binding.root
        }
        else{
            val view = inflater.inflate(R.layout.fragment_pop_up_dialog2, container, false)
            return view
        }
    }

    interface Dialog2{
        fun cancel()
        fun ok()
    }

    companion object {
        @JvmStatic
        fun newInstance( title: String, param2: Int) =
            PopUpDialgoFragment().apply {
                arguments = Bundle().apply {
                    putString("title", title)
                    putInt(ARG_PARAM2, param2)
                }
            }
    }
}

