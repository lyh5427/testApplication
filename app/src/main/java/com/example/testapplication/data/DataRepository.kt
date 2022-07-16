package com.example.testapplication.data

import com.example.testapplication.ui.viewmodeltest.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class DataRepository {
    private var a : Int = 0

    fun count() : Flow<Any> {
        return flow{
            for(i in 0..9){
                delay(500)
                emit(i)
            }
        }.flowOn(Dispatchers.Main)
    }

    fun sealedTest( a : Int) : Event {
        return if(a != 1){
            Event.Err("Error")
        }
        else{
            Event.response(Test(1,"213"))
        }
    }
}