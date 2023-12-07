package com.graphql.task.ui.navigation

import com.graphql.test.UsersDetailsQuery

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Favourite : Screen("fav")
    object Detail : Screen("details/{userId}") {
        fun createRoute(userId: String) = "details/$userId"
    }
    object CreateEditUser : Screen("CreateEditUser/{userData}") {
        fun createRoute(userData: String) = "CreateEditUser/$userData"
    }
}