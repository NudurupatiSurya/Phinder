package com.surya.findmedicines.ui.pharma_requests

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is pharma_requests Fragment"
    }
    val text: LiveData<String> = _text
}