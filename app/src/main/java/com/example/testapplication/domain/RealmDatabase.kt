package com.example.testapplication.domain

import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RealmDatabase @Inject constructor(){
    var realm = Realm.getInstance(
        RealmConfiguration.Builder()
        .name("antiscam.pushnoti_list")
        .schemaVersion(1)
        .deleteRealmIfMigrationNeeded()
        .build())
}