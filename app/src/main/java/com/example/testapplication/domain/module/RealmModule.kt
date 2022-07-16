package com.example.testapplication.domain.module

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Qualifier
import javax.inject.Singleton


@Qualifier
annotation class AA

@Qualifier
annotation class BB

@InstallIn(SingletonComponent::class)
@Module
object RealmModule{
    @AA
    @Provides
    @Singleton
    fun providesAARealm(@ApplicationContext context : Context) : Realm{
        return Realm.getInstance(RealmConfiguration.Builder()
            .name("aa")
            .schemaVersion(1)
            .deleteRealmIfMigrationNeeded()
            .build())
    }

    @BB
    @Provides
    @Singleton
    fun providesBBRealm(@ApplicationContext context : Context) : Realm{
        return Realm.getInstance(RealmConfiguration.Builder()
            .name("bb")
            .schemaVersion(1)
            .deleteRealmIfMigrationNeeded()
            .build())
    }


}