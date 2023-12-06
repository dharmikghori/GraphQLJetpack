package com.graphql.task.domain.data

import com.graphql.test.UserListQuery
import com.graphql.test.UsersDetailsQuery

interface UserRepository {

    suspend fun getUsers(): UserListQuery.Users
    suspend fun getUserDetails(userId: String): UsersDetailsQuery.User

}