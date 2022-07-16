package com.example.testapplication.data

import android.util.Log
import com.example.testapplication.domain.module.AA
import io.realm.Realm
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AaDataBase @Inject constructor(@AA private val aa : Realm) : DatabaseResource {

    override fun getAll() {
        Log.d("test", "${aa.configuration.realmFileName} BB")
    }

    override fun insert() {
        Log.d("test", "${aa.configuration.realmFileName} BB")
    }
}