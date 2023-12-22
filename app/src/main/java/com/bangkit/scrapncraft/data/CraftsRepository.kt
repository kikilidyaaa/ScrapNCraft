package com.bangkit.scrapncraft.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.bangkit.scrapncraft.data.remote.response.CraftsDetailResponse
import com.bangkit.scrapncraft.data.remote.response.CraftsResponse
import com.bangkit.scrapncraft.data.remote.response.DataDetailItem
import com.bangkit.scrapncraft.data.room.CraftsDatabase
import com.bangkit.scrapncraft.data.remote.response.DataItem
import com.bangkit.scrapncraft.data.remote.response.ErrorResponse
import com.bangkit.scrapncraft.data.remote.retrofit.ApiService
import com.bangkit.scrapncraft.data.room.CraftsDao
import com.bangkit.scrapncraft.utils.AppExecutors
import com.bangkit.scrapncraft.utils.Result
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

class CraftsRepository private constructor(
    private val apiService: ApiService,
    private val craftsDao: CraftsDao,
    private val appExecutors: AppExecutors
) {
    private val result = MediatorLiveData<Result<List<DataItem>>>()

    fun getCrafts(): LiveData<Result<List<DataItem>>> {
        result.value = Result.Loading
        val client = apiService.getCrafts()
        client.enqueue(object : Callback<CraftsResponse> {
            override fun onResponse(call: Call<CraftsResponse>, response: Response<CraftsResponse>) {
                if (response.isSuccessful) {
                    val data = response.body()?.data
                    val craftsList = ArrayList<DataItem>()
                    appExecutors.diskIO.execute {
                        data?.forEach { data ->
                            val isViewed = craftsDao.isCraftsViewed(data.title)
                            val crafts = DataItem(
                                data.itemId,
                                data.title,
                                data.desc,
                                data.category,
                                isViewed,
                                System.currentTimeMillis()
                            )
                            craftsList.add(crafts)
                        }
                        craftsDao.deleteAll()
                        craftsDao.insertCrafts(craftsList)
                    }
                }
            }

            override fun onFailure(call: Call<CraftsResponse>, t: Throwable) {
                result.value = Result.Error(t.message.toString())
            }
        })
        val localData = craftsDao.getCrafts()
        result.addSource(localData) { newData: List<DataItem> ->
            result.value = Result.Success(newData)
        }
        return result
    }

    fun getRecentlyViewedCrafts(): LiveData<List<DataItem>> {
        return craftsDao.getRecentlyViewedCrafts()
    }

    fun deleteOldRecentlyViewedCrafts(timestamp: Long) {
        appExecutors.diskIO.execute {
            craftsDao.deleteOldRecentlyViewedCrafts(timestamp)
        }
    }

    companion object {
        @Volatile
        private var instance: CraftsRepository? = null
        fun getInstance(
            apiService: ApiService,
            craftsDao: CraftsDao,
            appExecutors: AppExecutors
        ): CraftsRepository =
            instance ?: synchronized(this) {
                instance ?: CraftsRepository(apiService, craftsDao, appExecutors)
            }.also { instance = it }
    }
}