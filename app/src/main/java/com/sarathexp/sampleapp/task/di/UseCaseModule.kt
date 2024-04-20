package com.sarathexp.sampleapp.task.di

import com.sarathexp.sampleapp.task.domain.repository.AuthRepository
import com.sarathexp.sampleapp.task.domain.repository.UserRepository
import com.sarathexp.sampleapp.task.domain.use_case.auth.AuthUseCases
import com.sarathexp.sampleapp.task.domain.use_case.auth.CheckLogin
import com.sarathexp.sampleapp.task.domain.use_case.auth.Login
import com.sarathexp.sampleapp.task.domain.use_case.user.GetUsers
import com.sarathexp.sampleapp.task.domain.use_case.user.UserUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun providesUserUseCases(repository: UserRepository): UserUseCases {
        return UserUseCases(getUsers = GetUsers(repository))
    }

    @Provides
    @Singleton
    fun providesAuthUseCases(repository: AuthRepository): AuthUseCases {
        return AuthUseCases(login = Login(repository), checkLogin = CheckLogin(repository))
    }
}
