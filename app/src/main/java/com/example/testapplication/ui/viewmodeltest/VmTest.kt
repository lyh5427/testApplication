package com.example.testapplication.ui.viewmodeltest

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.testapplication.R
import com.example.testapplication.databinding.ActivityVmTestBinding

import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.testapplication.ui.PopUpDialgoFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class VmTest() : AppCompatActivity(), PopUpDialgoFragment.Dialog2 {
    private lateinit var vmBinding : ActivityVmTestBinding
    private val model : VmTestViewModel by viewModels()

    //리싸이클뷰 테스트
    private lateinit var recycleViewTestAdapter : RecycleViewTestAdapter

    fun handleEvent(e : Event){
        when(e){
            is Event.response ->{
                Log.d("1", "test Success")
            }
            is Event.Err ->{
                Log.d("2", "test Error")
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vmBinding = DataBindingUtil.setContentView(this, R.layout.activity_vm_test)
        vmBinding.lifecycleOwner = this
        vmBinding.vmTestViewModel = model

        model.bindingTest3()

        vmBinding.btn1.setOnClickListener {
            model.changeStateFlow()
        }

        lifecycleScope.launchWhenStarted {
            model.stateFlow.collectLatest {
                vmBinding.tv1.text = it.toString()
                Log.d("a", "StateFlow : $it")
            }
        }

        lifecycleScope.launchWhenStarted {
            delay(5000)
            model.stateFlow.collectLatest {
                Log.d("b", "StateFlow : $it")
            }
        }


        vmBinding.btn2.setOnClickListener {
            model.chageLiveData()
        }

        model.livaDataText.observe(this, {
            Log.d("test6", "LiveData : $it")
        })

        model.testLiveData.observe(this, {
            Log.d("test6", "LiveData : $it")
        })

        vmBinding.textbtn.setOnClickListener {
            model.bindingTest2()
            //model.sealedTest()
            //model.clickBtn()
        }
        //리싸이클뷰 테스트

        //sealed Test
        lifecycleScope.launchWhenStarted {
            model.sharedFlow.collectLatest {
                handleEvent(it)
            }
        }



        //팝업테스트
        supportFragmentManager.beginTransaction().replace(R.id.frag, Home()).commit()

        vmBinding.popupbtn.setOnClickListener {
            val popUp = PopUpDialgoFragment.newInstance("dididididididididi", 1)
            popUp.isCancelable = false

            supportFragmentManager.beginTransaction().add(popUp, "gkgk").commit()
            vmBinding.textbtn.text ="123412412"

        }




    }

    override fun cancel() {
        Log.d("popupTest", "MainActivity")

    }

    override fun ok() {

        Log.d("popupTest", "MainActivity")
    }
}