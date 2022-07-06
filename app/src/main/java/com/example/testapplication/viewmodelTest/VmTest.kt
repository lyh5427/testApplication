package com.example.testapplication.viewmodelTest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Adapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import com.example.testapplication.R
import com.example.testapplication.databinding.ActivityVmTestBinding

import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapplication.data.Test
import com.example.testapplication.home
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class VmTest : AppCompatActivity() {
    private lateinit var vmBinding : ActivityVmTestBinding
    private val model : VmTestViewModel by viewModels()

    //리싸이클뷰 테스트
    private lateinit var recycleViewTestAdapter : RecycleViewTestAdapter

    fun handleEvent(e :Event){
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
            model.sealedTest()
            //model.clickBtn()
        }
        //리싸이클뷰 테스트
        supportFragmentManager.beginTransaction().replace(R.id.frag, home()).commit()


        //sealed Test
        lifecycleScope.launchWhenStarted {
            model.sharedFlow.collectLatest {
                handleEvent(it)
            }
        }






    }
}