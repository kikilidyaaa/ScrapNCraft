package com.bangkit.scrapncraft.ui.main.home.listcrafts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.scrapncraft.data.CraftsRepository
import com.bangkit.scrapncraft.data.remote.response.DataItem

class ListCraftsViewModel(private val craftsRepository: CraftsRepository) : ViewModel() {
    private val _craftsList = MutableLiveData<List<DataItem>>()
    val craftsList: LiveData<List<DataItem>> = _craftsList

    fun getCrafts() = craftsRepository.getCrafts()

}