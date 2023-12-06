package com.graphql.task.di

import com.apollographql.apollo3.ApolloClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideApolloClient(): ApolloClient = ApolloClient.Builder()
        .serverUrl("https://graphqlzero.almansi.me/api")
        .build()

}
