package com.example.testapplication.domain.module

import android.content.Context
import com.example.testapplication.data.DataRepository
import com.example.testapplication.data.DataRepositorySource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
abstract class RepoModule {

    @Binds
    @Singleton
    abstract fun providesRepo(repo : DataRepository) : DataRepositorySource
}