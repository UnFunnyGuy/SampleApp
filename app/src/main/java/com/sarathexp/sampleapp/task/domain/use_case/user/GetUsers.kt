package com.sarathexp.sampleapp.task.domain.use_case.user

import androidx.paging.PagingData
import com.sarathexp.sampleapp.task.app.common.BaseUseCase
import com.sarathexp.sampleapp.task.data.model.User
import com.sarathexp.sampleapp.task.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class GetUsers(private val userRepository: UserRepository) :
    BaseUseCase<Nothing, PagingData<User>> {

    override fun performStreaming(params: Nothing?): Flow<PagingData<User>> {
        return userRepository.getUsers()
    }
}
