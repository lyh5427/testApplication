package com.example.testapplication.ui.viewmodelTest.viewmodeltest

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.testapplication.R
import com.example.testapplication.databinding.FragmentHomeBinding
import com.example.testapplication.ui.PopUpDialgoFragment
import com.example.testapplication.ui.viewmodelTest.viewmodeltest.VmTestViewModel
import kotlinx.coroutines.flow.collectLatest

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [home.newInstance] factory method to
 * create an instance of this fragment.
 */

fun handleEvent(e : Event){
    when(e){
        is Event.response ->{
            Log.d("1", "test Success")
        }
        is Event.Err ->{
            Log.d("2", "test Error 2312312312321")
        }
    }

}

class home : Fragment(), PopUpDialgoFragment.Dialog2 {
    lateinit var h : FragmentHomeBinding
    private lateinit var callback : OnBackPressedCallback
    private val vmViewModel : VmTestViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        h = DataBindingUtil.inflate(inflater, R.layout.fragment_home,container,false)
        h.vm = vmViewModel
        h.button3.setOnClickListener {
            val popUp = PopUpDialgoFragment.newInstance("dididididididididi", 1)
            popUp.addListener(this)
            childFragmentManager.beginTransaction().add(popUp,"awd").commit()
        }

        lifecycleScope.launchWhenStarted {
            vmViewModel.sharedFlow.collectLatest {
                handleEvent(it)
            }
        }

        return h.root
    }
    fun bindingTest2() {
        Log.d("text : ",  "awwadawdaw")
        vmViewModel.liveData.value!!.name ="efa"
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                Log.d("213","백프레스 성공")
                requireActivity().supportFragmentManager.beginTransaction().replace(R.id.frag, home2()).commit()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onDetach() {
        super.onDetach()
        callback.remove()
    }

    override fun cancel() {
        Log.d("popupTest", "2222222222222")

    }

    override fun ok() {

        Log.d("popupTest", "1111111111111")
    }
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            home().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}