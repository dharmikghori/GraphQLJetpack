package com.graphql.task.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Favourite : Screen("fav")
    object Detail : Screen("details/{userId}") {
        fun createRoute(userId: String) = "details/$userId"
    }
}