package com.bangkit.scrapncraft.data.remote.retrofit

import com.bangkit.scrapncraft.data.remote.response.CraftsDetailResponse
import com.bangkit.scrapncraft.data.remote.response.CraftsResponse
import com.bangkit.scrapncraft.data.remote.response.DataDetailItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("scrap")
    fun getCrafts(): Call<CraftsResponse>

    @GET("scrap/title")
    fun getDetailCrafts(@Query("Title") title: String): Call<CraftsDetailResponse>

    @GET("scrap/title")
    fun getResultSearchTitle(
        @Query("Title") title: String
    ): Call<CraftsResponse>

    @GET("scrap/like")
    fun getResultSearchTools(
        @Query("Tools") tools: String
    ): Call<CraftsResponse>

    @GET("scrap/category")
    fun getResultSearchCategory(
        @Query("Category") category: String
    ): Call<CraftsResponse>


}