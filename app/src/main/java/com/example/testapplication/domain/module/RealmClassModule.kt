package com.example.testapplication.domain.module

import com.example.testapplication.data.AaDataBase

import com.example.testapplication.data.BbDataBase
import com.example.testapplication.data.DatabaseResource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
annotation class Aadata

@Qualifier
annotation class BbData


@InstallIn(SingletonComponent::class)
@Module
abstract class AaMudule{
    @Aadata
    @Binds
    @Singleton
    abstract fun bindAaDataBase( aa : AaDataBase ) : DatabaseResource
}


@InstallIn(SingletonComponent::class)
@Module
abstract class BbMudule{
    @BbData
    @Binds
    @Singleton
    abstract fun bindBbDataBase( Bb : BbDataBase ) : DatabaseResource
}