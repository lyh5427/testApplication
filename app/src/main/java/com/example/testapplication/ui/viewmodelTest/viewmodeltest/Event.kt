package com.example.testapplication.ui.viewmodelTest.viewmodeltest

import com.example.testapplication.data.Test

sealed class Event {
    data class response(val test : Test) : Event()
    data class Err(val err : String) : Event()
}