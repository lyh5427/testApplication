package com.example.testapplication.ui.base

import android.provider.ContactsContract
import androidx.lifecycle.ViewModel
import com.example.testapplication.data.DataRepository
import com.example.testapplication.data.DataRepositorySource
import com.example.testapplication.data.DatabaseResource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor(repo : DataRepositorySource) : ViewModel() {


}