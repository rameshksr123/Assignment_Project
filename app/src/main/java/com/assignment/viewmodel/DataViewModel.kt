package com.assignment.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.assignment.model.Data
import com.assignment.repository.ProjectRepo

class DataViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ProjectRepo.instance
    var dataListObservable: LiveData<Data?>

    override fun onCleared() {
        super.onCleared()
    }

    companion object {
        private const val TAG = "MainActivityViewModel"
    }

    init {
        dataListObservable = repository.dataList
    }
}