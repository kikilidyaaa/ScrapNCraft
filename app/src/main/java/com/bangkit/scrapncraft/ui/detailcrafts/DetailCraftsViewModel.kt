package com.bangkit.scrapncraft.ui.detailcrafts

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.bangkit.scrapncraft.data.CraftsRepository
import com.bangkit.scrapncraft.data.remote.response.CraftsDetailResponse
import com.bangkit.scrapncraft.data.remote.response.DataDetailItem
import com.bangkit.scrapncraft.data.remote.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailCraftsViewModel : ViewModel() {
    private val _craftsDetail = MutableLiveData<DataDetailItem>()
    val craftsDetail: LiveData<DataDetailItem> = _craftsDetail

    private val _tools = MutableLiveData<List<String>>()
    val tools: LiveData<List<String>> = _tools

    private val _steps = MutableLiveData<List<String>>()
    val steps: LiveData<List<String>> = _steps

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun getDetailCrafts(title: String) {
        _loading.value = true
        val apiService = ApiConfig.getApiService()
        apiService.getDetailCrafts(title).enqueue(object : Callback<CraftsDetailResponse> {
            override fun onResponse(
                call: Call<CraftsDetailResponse>,
                response: Response<CraftsDetailResponse>
            ) {
                _loading.value = false
                if (response.isSuccessful) {
                    val craftsResponse = response.body()
                    craftsResponse?.let {
                        if (it.data.isNotEmpty()) {
                            val crafts = it.data[0]
                            _craftsDetail.value = crafts
                            _tools.value = crafts.getToolsList()
                            _steps.value = crafts.getStepsList()
                        }
                    }
                } else {
                    _errorMessage.value = "Network request failed with code: ${response.code()}"
                }
            }

            override fun onFailure(call: Call<CraftsDetailResponse>, t: Throwable) {
                _loading.value = false
                _errorMessage.value = "Network request failed: ${t.message}"
                Log.e("DetailCraftsActivity", "Error: ${t.message}")
            }
        })
    }
}