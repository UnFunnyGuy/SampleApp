package com.sarathexp.sampleapp.task.data.model

import com.google.gson.annotations.SerializedName

data class UserData(
    val page: Int,
    @SerializedName("per_page") val perPage: Int,
    val total: Int,
    @SerializedName("total_pages") val totalPages: Int,
    val data: List<User>,
    val support: Support
)

data class UserListResponse(val data: List<User>)
