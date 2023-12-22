package com.bangkit.scrapncraft.ui.main

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bangkit.scrapncraft.data.CraftsRepository
import com.bangkit.scrapncraft.di.Injection
import com.bangkit.scrapncraft.ui.detailcrafts.DetailCraftsViewModel
import com.bangkit.scrapncraft.ui.main.explore.ExploreViewModel
import com.bangkit.scrapncraft.ui.main.home.listcrafts.ListCraftsViewModel
import com.bangkit.scrapncraft.ui.main.home.recentlycrafts.RecentlyCraftsViewModel
import com.bangkit.scrapncraft.utils.AppExecutors

class MainViewModelFactory private constructor(private val craftsRepository: CraftsRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListCraftsViewModel::class.java)) {
            return ListCraftsViewModel(craftsRepository) as T
        }
        if (modelClass.isAssignableFrom(RecentlyCraftsViewModel::class.java)) {
            return RecentlyCraftsViewModel(craftsRepository) as T
        }
        if (modelClass.isAssignableFrom(DetailCraftsViewModel::class.java)) {
            return DetailCraftsViewModel() as T
        }
        if (modelClass.isAssignableFrom(ExploreViewModel::class.java)) {
            return ExploreViewModel(craftsRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: MainViewModelFactory? = null
        fun getInstance(context: Context): MainViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: MainViewModelFactory(Injection.provideCraftsRepository(context))
            }.also { instance = it }
    }
}