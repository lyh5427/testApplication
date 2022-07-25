package com.example.testapplication.data

import android.content.Context
import com.example.testapplication.ui.viewmodeltest.Event
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepository @Inject constructor() : DataRepositorySource {
    private var a : Int = 0

    override fun count() : Flow<Any> {
        return flow{
            for(i in 0..9){
                delay(500)
                emit(i)
            }
        }.flowOn(Dispatchers.Main)
    }

    override fun sealedTest( a : Int) : Event {
        return if(a != 1){
            Event.Err("Error")
        }
        else{
            Event.response(Test(1,"213"))
        }
    }
}