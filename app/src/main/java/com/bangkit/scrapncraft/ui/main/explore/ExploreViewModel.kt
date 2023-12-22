package com.bangkit.scrapncraft.ui.main.explore

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.scrapncraft.data.CraftsRepository
import com.bangkit.scrapncraft.data.remote.response.CraftsResponse
import com.bangkit.scrapncraft.data.remote.response.DataItem
import com.bangkit.scrapncraft.data.remote.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExploreViewModel(private val craftsRepository: CraftsRepository) : ViewModel() {
    private val _craftsList = MutableLiveData<List<DataItem>>()
    val craftsList: LiveData<List<DataItem>> = _craftsList

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    fun searchTitleCrafts(title: String) {
        _loading.value = true
        val apiService = ApiConfig.getApiService()
        apiService.getResultSearchTitle(title).enqueue(object : Callback<CraftsResponse> {
            override fun onResponse(
                call: Call<CraftsResponse>,
                response: Response<CraftsResponse>
            ){
                _loading.value = false
                if (response.isSuccessful) {
                    val usersResponse = response.body()
                    usersResponse?.let {
                        _craftsList.value = it.data
                    }
                } else {
                    Log.e("ExploreFragment", "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<CraftsResponse>, t: Throwable) {
                _loading.value = false
                Log.e("ExploreFragment", "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun searchToolsCrafts(tools: String) {
        _loading.value = true
        val apiService = ApiConfig.getApiService()
        apiService.getResultSearchTools(tools).enqueue(object : Callback<CraftsResponse> {
            override fun onResponse(
                call: Call<CraftsResponse>,
                response: Response<CraftsResponse>
            ){
                _loading.value = false
                if (response.isSuccessful) {
                    val usersResponse = response.body()
                    usersResponse?.let {
                        _craftsList.value = it.data
                    }
                } else {
                    Log.e("ExploreFragment", "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<CraftsResponse>, t: Throwable) {
                _loading.value = false
                Log.e("ExploreFragment", "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun searchCategoryCrafts(category: String) {
        _loading.value = true
        val apiService = ApiConfig.getApiService()
        apiService.getResultSearchCategory(category).enqueue(object : Callback<CraftsResponse> {
            override fun onResponse(
                call: Call<CraftsResponse>,
                response: Response<CraftsResponse>
            ){
                _loading.value = false
                if (response.isSuccessful) {
                    val usersResponse = response.body()
                    usersResponse?.let {
                        _craftsList.value = it.data
                    }
                } else {
                    Log.e("ExploreFragment", "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<CraftsResponse>, t: Throwable) {
                _loading.value = false
                Log.e("ExploreFragment", "onFailure: ${t.message.toString()}")
            }
        })
    }
}