package com.graphql.task.di

import com.graphql.task.domain.data.UserRepository
import com.graphql.task.domain.data.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun dataRepository(repositoryImpl: UserRepositoryImpl): UserRepository
}