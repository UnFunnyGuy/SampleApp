package com.sarathexp.sampleapp.task.data

import com.sarathexp.sampleapp.task.data.local.AppDataStore
import com.sarathexp.sampleapp.task.domain.RequestError
import com.sarathexp.sampleapp.task.domain.RequestResult
import com.sarathexp.sampleapp.task.domain.repository.AuthRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val dataStore: AppDataStore) : AuthRepository {

    override suspend fun login(
        userName: String,
        password: String
    ): RequestResult<Boolean, RequestError.Network> {
        delay(2500)
        val random = 2 > 1 // Random.nextBoolean()
        return if (random) {
            dataStore.saveLoggedIn()
            RequestResult.Success(true)
        } else {
            RequestResult.Error(RequestError.Network.entries.random())
        }
    }

    override suspend fun isLoggedIn(): Boolean {
        return dataStore.getLoggedIn()
    }
}
