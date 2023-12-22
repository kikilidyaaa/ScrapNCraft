package com.bangkit.scrapncraft.di

import android.content.Context
import com.bangkit.scrapncraft.data.CraftsRepository
import com.bangkit.scrapncraft.data.room.CraftsDatabase
import com.bangkit.scrapncraft.data.remote.retrofit.ApiConfig
import com.bangkit.scrapncraft.utils.AppExecutors

object Injection {
    fun provideCraftsRepository(context: Context): CraftsRepository {
        val apiService = ApiConfig.getApiService()
        val database = CraftsDatabase.getInstance(context)
        val dao = database.craftsDao()
        val appExecutors = AppExecutors()
        return CraftsRepository.getInstance(apiService, dao, appExecutors)
    }
}