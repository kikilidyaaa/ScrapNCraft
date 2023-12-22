package com.bangkit.scrapncraft.ui.main.library

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LibraryViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "No Data Available"
    }
    val text: LiveData<String> = _text
}