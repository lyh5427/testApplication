package com.example.testapplication.ui.viewmodelTest.viewmodeltest

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.testapplication.data.DataRepository
import com.example.testapplication.data.StringTest
import com.example.testapplication.data.Test
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class a (
    val name : String ?=  ":",
    val number : String?
    )

class VmTestViewModel(application : Application) : AndroidViewModel(application) {
    @SuppressLint("StaticFieldLeak")

    private var dataRepo = DataRepository()
    private val _liveData = MutableLiveData<ArrayList<a>>()

    private val context = getApplication<Application>().applicationContext
    private var _liveDataText : MutableLiveData<Int> = MutableLiveData(0)
    val livaDataText : LiveData<Int> get() = _liveDataText

    private var _stateFlow = MutableStateFlow(0)
    val stateFlow : StateFlow<Int> get() = _stateFlow

    private var _testFlow = MutableStateFlow<Int>(0)
    val testFlow : StateFlow<Int> get() = _testFlow

    private var a : MutableStateFlow<Int>? = null

    private var _testLiveData : MutableLiveData<Int> = MutableLiveData()
    val testLiveData get() = _testLiveData

    //리싸이클뷰테스트
    private var _title = MutableLiveData<ArrayList<Test>>()
    val title : LiveData<ArrayList<Test>> = _title

    private var _sharedFlow = MutableSharedFlow<Event>()
    val sharedFlow = _sharedFlow

    private var items = ArrayList<Test>()


    init{
        items = arrayListOf(
            Test(1, " 1입니다."),
            Test(2, " 2입니다."),
            Test(3, " 3입니다."),
            Test(4, " 4입니다."),
            Test(5, " 5입니다."),
            Test(6, " 6입니다.")
        )
        _title.value = items
    }
    //sealed test
   fun sealedTest(){
       viewModelScope.launch {
           _sharedFlow.emit(dataRepo.sealedTest(2))
       }
   }



    fun clickBtn(){
        /*items[2].text = "바뀜."
        items[2].text = "wwwwwww"
        _title.value = items*/
        _title.value!![2].text = "3414"
    }

//cold / hot Test
    fun changeStateFlow(){
        viewModelScope.launch {
            for( i in 0..9){
                _stateFlow.emit(i)
                delay(1000)
            }
        }

    }

    fun chageLiveData(){
        viewModelScope.launch {
            for(i in 0..9){
                _liveDataText.value = i
                delay(500)
            }
        }

    }

    fun plusLiveData(){
        viewModelScope.launch {
            _liveDataText.value = 10
        }
        Log.d("21321312","12312312312")
    }
/*
    fun testDialog(){
        val items = arrayOf("1개", "2개", "3개", "4개", "5개")
        val builder =  AlertDialog.Builder(context)
        builder.setTitle("잠금해제 개수 선택")
        builder.setItems(items){dialog, which ->
            when(which){
                in 0..4->{
                    _liveDataText.value = which
                }
            }
        }
        builder.show()
    }*/

    fun bindingTest(s : StringTest) {
        Log.d("text : ", "${s.string} awwadawdaw")
    }
}