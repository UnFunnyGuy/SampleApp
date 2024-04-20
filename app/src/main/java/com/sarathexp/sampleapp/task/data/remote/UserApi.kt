package com.sarathexp.sampleapp.task.data.remote

import androidx.annotation.Keep
import com.sarathexp.sampleapp.task.data.model.UserListResponse
import retrofit2.http.GET
import retrofit2.http.Query

@Keep
interface UserApi {
    @GET("users")
    suspend fun getUsers(
        @Query("page") page: Int = 1,
        @Query("per_page") pageLimit: Int = 6,
    ): UserListResponse
}
