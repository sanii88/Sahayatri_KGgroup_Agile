package com.sunny.sahayatribookingsewa.ui.boarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BoardingViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is boarding Fragment"
    }
    val text: LiveData<String> = _text
}