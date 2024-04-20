package com.sarathexp.sampleapp.task.domain.repository

import androidx.paging.PagingData
import com.sarathexp.sampleapp.task.data.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUsers(): Flow<PagingData<User>>
}
