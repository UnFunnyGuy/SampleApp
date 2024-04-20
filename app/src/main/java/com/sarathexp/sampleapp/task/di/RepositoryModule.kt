package com.sarathexp.sampleapp.task.di

import com.sarathexp.sampleapp.task.data.AuthRepositoryImpl
import com.sarathexp.sampleapp.task.data.UserRepositoryImpl
import com.sarathexp.sampleapp.task.domain.repository.AuthRepository
import com.sarathexp.sampleapp.task.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds abstract fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds abstract fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository
}
