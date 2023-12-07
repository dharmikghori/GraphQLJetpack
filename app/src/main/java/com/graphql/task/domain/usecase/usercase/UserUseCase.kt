package com.graphql.task.domain.usecase.usercase

import com.graphql.test.UserListQuery
import com.graphql.test.UsersDetailsQuery
import com.graphql.task.domain.Result
import com.graphql.task.domain.data.UserRepository
import com.graphql.test.CreateUserMutation
import com.graphql.test.UpdateUserMutation
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

    suspend fun deleteUserFromId(userId: String): Result<Boolean> {
        return try {
            Result.Success(repository.deleteUserFromId(userId))
        } catch (exception: Exception) {
            Result.Error(exception.message ?: "Unable to delete user")
        }
    }

    suspend fun updateUser(updateUserMutation: UpdateUserMutation.UpdateUser): Result<UpdateUserMutation.UpdateUser> {
        return try {
            Result.Success(repository.updateUser(updateUserMutation))
        } catch (exception: Exception) {
            Result.Error(exception.message ?: "Unable to update user")
        }
    }
    suspend fun createUser(createUser: CreateUserMutation.CreateUser): Result<CreateUserMutation.CreateUser> {
        return try {
            Result.Success(repository.createUser(createUser))
        } catch (exception: Exception) {
            Result.Error(exception.message ?: "Unable to Create user")
        }
    }
}