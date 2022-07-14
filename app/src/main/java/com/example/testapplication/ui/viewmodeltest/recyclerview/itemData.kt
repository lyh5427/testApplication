package com.example.testapplication.ui.viewmodeltest.recyclerview

class itemData {
    var a : Int = 0
    var s : String = "0000"

    fun itemData(){
        this.a = 0
        this.s = "2wrefesfs"
    }

    fun geta() : Int {
        return a
    }

    fun seta(seta : Int){
        this.a = seta
    }

    fun gets() : String{
        return s
    }

    fun sets(sets : String){
        this.s = sets
    }
}