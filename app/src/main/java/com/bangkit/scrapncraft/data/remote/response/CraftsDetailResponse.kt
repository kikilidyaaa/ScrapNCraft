package com.bangkit.scrapncraft.data.remote.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class CraftsDetailResponse(

	@field:SerializedName("data")
	val data: List<DataDetailItem>,

	@field:SerializedName("message")
	val message: String
) : Parcelable

@Parcelize
data class DataDetailItem(

	@field:SerializedName("ItemId")
	val itemId: Int,

	@field:SerializedName("Email")
	val email: String,

	@field:SerializedName("Title")
	val title: String,

	@field:SerializedName("Desc")
	val desc: String,

	@field:SerializedName("Category")
	val category: String,

	@field:SerializedName("Tools")
	val tools: String,

	@field:SerializedName("Steps")
	val steps: String,

	@field:SerializedName("Status")
	val status: String
) : Parcelable {

	fun getToolsList(): List<String> {
		return tools.split(", ")
	}

	fun getStepsList(): List<String> {
		return steps.split(", ")
	}
}
