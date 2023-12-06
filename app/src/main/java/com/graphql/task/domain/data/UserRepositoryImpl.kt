package com.graphql.task.domain.data

import com.apollographql.apollo3.ApolloClient
import com.graphql.test.UserListQuery
import com.graphql.test.UsersDetailsQuery
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apolloClient: ApolloClient
) :
    UserRepository {
    override suspend fun getUsers(): UserListQuery.Users {
        return apolloClient.query(UserListQuery()).execute().data?.users!!
    }

    override suspend fun getUserDetails(userId:String): UsersDetailsQuery.User {
        return apolloClient.query(UsersDetailsQuery(userId)).execute().data?.user!!
    }
}
