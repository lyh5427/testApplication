package com.example.testapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.testapplication.R

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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            title = it.getString("title")
            param2 = it.getInt(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if(param2 == 1){
            val view = inflater.inflate(R.layout.fragment_pop_up_dialgo, container, false)
            return view
        }
        else{
            val view = inflater.inflate(R.layout.fragment_pop_up_dialog2, container, false)
            return view
        }
    }

    companion object {
        @JvmStatic
        fun newInstance( title: String, param2: String) =
            PopUpDialgoFragment().apply {
                arguments = Bundle().apply {
                    putString("title", title)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

interface Dialog{
    fun cancel()
    fun ok()
}