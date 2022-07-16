package com.example.testapplication.data

interface DatabaseResource {
    fun <T> getAll( a : Class<T> ) : ArrayList<Class<T>>
    fun insert()
}