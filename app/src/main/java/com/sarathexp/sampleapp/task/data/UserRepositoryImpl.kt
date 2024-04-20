package com.sarathexp.sampleapp.task.data

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sarathexp.sampleapp.task.data.model.User
import com.sarathexp.sampleapp.task.data.remote.UserApi
import com.sarathexp.sampleapp.task.domain.repository.UserRepository
import com.sarathexp.sampleapp.task.util.DummyDataProvider
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userApi: UserApi) : UserRepository {

    override fun getUsers(): Flow<PagingData<User>> {
        return Pager(
                config = PagingConfig(6),
                pagingSourceFactory = {
                    SamplePagingSource { page ->
                        try {
                            val response = userApi.getUsers(page)
                            response.data
                        } catch (e: Exception) {
                            Log.e("UserRepositoryImpl", "Error: $e")
                            DummyDataProvider.getUsers(page)
                        }
                    }
                }
            )
            .flow
    }
}
