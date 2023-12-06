package com.graphql.task.domain.usecase.usercase

import com.graphql.test.UserListQuery
import com.graphql.test.UsersDetailsQuery
import com.graphql.task.domain.Result
import com.graphql.task.domain.data.UserRepository
import javax.inject.Inject

class UserUseCase @Inject constructor(private val repository: UserRepository) {

    suspend fun userList(): Result<UserListQuery.Users> {
        return try {
            Result.Success(repository.getUsers())
        } catch (exception: Exception) {
            Result.Error(exception.message ?: "Error Getting Users")
        }
    }

    suspend fun userDetails(userId: String): Result<UsersDetailsQuery.User> {
        return try {
            Result.Success(repository.getUserDetails(userId))
        } catch (exception: Exception) {
            Result.Error(exception.message ?: "Error Getting UserDetails")
        }
    }
}