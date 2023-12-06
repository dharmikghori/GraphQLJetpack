package com.graphql.task.ui.user

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.graphql.task.ui.composables.UserCard
import com.graphql.task.ui.navigation.Screen
import com.graphql.task.user.UserViewModel

@Composable
fun UsersList(navController: NavHostController) {
    val viewModel = hiltViewModel<UserViewModel>()
    val uiState = viewModel.uiState.collectAsState()
    LaunchedEffect(key1 = 1) {
        viewModel.getUsers()
    }

    Box(
        Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {

        when (val state = uiState.value) {
            is UserViewModel.UiState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .testTag(
                            "Loading"
                        )
                )
            }

            is UserViewModel.UiState.LoadedUsers -> {
                val users = state.users
                if (users!!.isNotEmpty()) {
                    LazyColumn {
                        items(users.size) {
                            users[it]?.let { user ->
                                UserCard(user = user) {
                                    navController.navigate(Screen.Detail.createRoute(user.id.toString()))
                                }
                            }
                        }
                    }
                }
            }

            is UserViewModel.UiState.Error -> {
                val errorMsg = state.message
                Text(
                    text = "Oops $errorMsg",
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            else -> {}
        }
    }
}
