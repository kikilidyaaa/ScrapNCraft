package com.bangkit.scrapncraft.ui.main.home.recentlycrafts

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bangkit.scrapncraft.data.CraftsRepository
import com.bangkit.scrapncraft.data.remote.response.DataItem
import com.bangkit.scrapncraft.utils.AppExecutors

class RecentlyCraftsViewModel(private val craftsRepository: CraftsRepository) : ViewModel() {
    fun getRecentlyViewedCrafts(): LiveData<List<DataItem>> {
        return craftsRepository.getRecentlyViewedCrafts()
    }

    fun deleteOldRecentlyViewedCrafts(timestamp: Long) {
        craftsRepository.deleteOldRecentlyViewedCrafts(timestamp)
    }
}