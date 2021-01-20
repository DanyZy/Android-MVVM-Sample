package com.aim.leantechtest.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aim.leantechtest.data.api.ApiInstance
import com.aim.leantechtest.data.repository.Repository
import com.aim.leantechtest.ui.main.viewmodel.MainViewModel

class ViewModelFactory(private val apiObj: ApiInstance) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(Repository(apiObj)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}