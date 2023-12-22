package com.bangkit.scrapncraft.data.remote.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Parcelize
data class CraftsResponse(

	@field:SerializedName("data")
	val data: List<DataItem>,

	@field:SerializedName("message")
	val message: String
) : Parcelable


@Entity(tableName = "crafts")
@Parcelize
data class DataItem(

	@field:SerializedName("ItemId")
	val itemId: Int,

	@field:PrimaryKey
	@field:SerializedName("Title")
	val title: String,

	@field:SerializedName("Desc")
	val desc: String,

	@field:SerializedName("Category")
	val category: String,

	@field:SerializedName("isViewed")
	var isViewed: Boolean = false,

	@field:SerializedName("ViewedTimestamp")
	var viewedTimestamp: Long = 0
) : Parcelable
