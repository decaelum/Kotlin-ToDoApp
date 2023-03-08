package com.yagizcgrgn.to_doapp.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TaskViewModel : ViewModel() {
    val name = MutableLiveData<String>()
    val desc = MutableLiveData<String>()
}