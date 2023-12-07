package com.graphql.task.domain.data

import com.graphql.test.CreateUserMutation
import com.graphql.test.DeleteUserMutation
import com.graphql.test.UpdateUserMutation
import com.graphql.test.UserListQuery
import com.graphql.test.UsersDetailsQuery

interface UserRepository {

    suspend fun getUsers(): UserListQuery.Users
    suspend fun getUserDetails(userId: String): UsersDetailsQuery.User
    suspend fun deleteUserFromId(userId: String): Boolean

    suspend fun updateUser(updateUserMutation: UpdateUserMutation.UpdateUser): UpdateUserMutation.UpdateUser
    suspend fun createUser(createMutation: CreateUserMutation.CreateUser): CreateUserMutation.CreateUser

}