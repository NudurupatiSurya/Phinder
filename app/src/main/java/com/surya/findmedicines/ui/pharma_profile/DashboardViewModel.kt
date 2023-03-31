package com.surya.findmedicines.ui.pharma_profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DashboardViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is pharma_profile Fragment"
    }
    val text: LiveData<String> = _text
}