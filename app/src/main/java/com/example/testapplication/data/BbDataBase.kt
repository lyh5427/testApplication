package com.example.testapplication.data

import android.util.Log
import com.example.testapplication.domain.module.AA
import com.example.testapplication.domain.module.BB
import io.realm.Realm
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class BbDataBase @Inject constructor(@BB private val bb : Realm) : DatabaseResource{
    override fun getAll() {
        Log.d("test", "${bb.configuration.realmFileName} BB")
    }

    override fun insert() {
        Log.d("test", "${bb.configuration.realmFileName} BB")
    }
}