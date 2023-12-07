package com.graphql.task.ui

import android.os.Build
import android.os.Parcelable
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.graphql.task.ui.user.UserDetails
import com.graphql.task.ui.user.UsersList
import com.graphql.task.ui.navigation.Screen
import com.graphql.task.ui.user.CreateEditUser
import com.graphql.test.UsersDetailsQuery

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun App() {
    val navController: NavHostController = rememberNavController()
    NavHost(
        modifier = Modifier.systemBarsPadding(),
        navController = navController,
        startDestination = Screen.Home.route
    ) {

        composable(Screen.Home.route) {
            UsersList(navController)
        }

        composable(Screen.Detail.route) {
            val userId = it.arguments?.getString("userId") ?: ""
            UserDetails(navController, userId)
        }

        composable(Screen.CreateEditUser.route) {
            val userInfo: UsersDetailsQuery.User? = it.arguments?.getParcelable(
                "userData",
                UsersDetailsQuery.User::class.java
            )
            CreateEditUser(navController, userInfo)
        }
    }
}