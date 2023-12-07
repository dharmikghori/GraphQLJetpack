package com.graphql.task.domain.data

import com.apollographql.apollo3.ApolloClient
import com.graphql.test.CreateUserMutation
import com.graphql.test.DeleteUserMutation
import com.graphql.test.UpdateUserMutation
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

    override suspend fun getUserDetails(userId: String): UsersDetailsQuery.User {
        return apolloClient.query(UsersDetailsQuery(userId)).execute().data?.user!!
    }

    override suspend fun deleteUserFromId(userId: String): Boolean {
        return apolloClient.mutation(DeleteUserMutation(userId)).execute().data?.deleteUser!!
    }

    override suspend fun updateUser(updateUserMutation: UpdateUserMutation.UpdateUser): UpdateUserMutation.UpdateUser {
        return apolloClient.mutation(
            UpdateUserMutation(
                strId = updateUserMutation.id.toString(),
                name = updateUserMutation.name.toString(),
                userName = updateUserMutation.username.toString(),
                email = updateUserMutation.email.toString(),
                phone = updateUserMutation.phone.toString(),
                website = updateUserMutation.website.toString()
            )
        ).execute().data?.updateUser!!
    }

    override suspend fun createUser(createMutation: CreateUserMutation.CreateUser): CreateUserMutation.CreateUser {
        return apolloClient.mutation(
            CreateUserMutation(
                name = createMutation.name.toString(),
                userName = createMutation.username.toString(),
                email = createMutation.email.toString(),
                phone = createMutation.phone.toString(),
                website = createMutation.website.toString()
            )
        ).execute().data?.createUser!!
    }


}
