package com.example.testapplication.data

import com.example.testapplication.ui.viewmodeltest.Event
import kotlinx.coroutines.flow.Flow

interface DataRepositorySource {

    fun count() : Flow<Any>

    fun sealedTest( a : Int) : Event

    fun c() : Int{
        return 1
    }
}