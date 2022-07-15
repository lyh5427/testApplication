package com.example.testapplication.domain.module

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RealmModule{
    @Provides
    @Singleton
    fun providesConfig(@ApplicationContext context : Context) : RealmConfiguration{
        Realm.init(context)
        return RealmConfiguration.Builder()
            .name("antiscam.pushnoti_list")
            .schemaVersion(1)
            .deleteRealmIfMigrationNeeded()
            .build()
    }

    @Provides
    @Singleton
    fun providesRealm(config : RealmConfiguration) : Realm{
        return Realm.getInstance(config)
    }


}