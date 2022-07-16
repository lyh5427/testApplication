package com.example.testapplication.data

import android.util.Log
import com.example.testapplication.domain.module.AA
import io.realm.Realm
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AaDataBase @Inject constructor(@AA private val aa : Realm) : DatabaseResource {

    override fun <T> getAll(a: Class<T>): ArrayList<Class<T>> {
        Log.d("test", "${aa.configuration.realmFileName} BB")
        return arrayListOf(test2("awd","!32")) as ArrayList<Class<T>>
    }

    override fun insert() {
        Log.d("test", "${aa.configuration.realmFileName} BB")
    }
}