package com.surya.findmedicines.ui.Responses

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DashboardViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Responses Fragment"
    }
    val text: LiveData<String> = _text
}