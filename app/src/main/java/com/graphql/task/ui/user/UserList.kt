package com.graphql.task.ui.user

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.gson.Gson
import com.graphql.task.theme.AppTheme
import com.graphql.task.ui.composables.PrimaryText
import com.graphql.task.ui.composables.UserCard
import com.graphql.task.ui.navigation.Screen
import com.graphql.task.ui.showToast
import com.graphql.task.user.UserViewModel
import com.graphql.test.R

@Composable
fun UsersList(navController: NavHostController) {
    val viewModel = hiltViewModel<UserViewModel>()
    val uiState = viewModel.uiState.collectAsState()
    viewModel.getUsers()
    Column {

        Row(
            Modifier
                .fillMaxWidth()
                .background(AppTheme.colors.fillSecondary)
                .padding(0.dp)
                .padding(10.dp)
        ) {
            PrimaryText(
                color = AppTheme.colors.white,
                text = stringResource(R.string.users),
                style = AppTheme.typography.semiBoldMontserrat20
            )
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
                                    UserCard(
                                        user = user,
                                        onClick = {
                                            navController.navigate(
                                                Screen.Detail.createRoute(user.id.toString())
                                            )
                                        },
                                        onDelete = {
                                            viewModel.deleteUserFromId(user.id.toString())
                                        })
                                }
                            }
                        }
                    }
                }

                is UserViewModel.UiState.Error -> {
                    val errorMsg = state.message
                    LocalContext.current.showToast(errorMsg)
                    PrimaryText(
                        style = AppTheme.typography.regularMontserrat14,
                        text = errorMsg,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                is UserViewModel.UiState.DeleteUser -> {
                    LocalContext.current.showToast(stringResource(R.string.user_deleted))
//                    viewModel.getUsers()
                }

                else -> {
                }
            }

            FloatingActionButton(
                containerColor = AppTheme.colors.fillSecondary,
                shape = RoundedCornerShape(100.dp),
                onClick = {
                    navController.navigate(
                        Screen.CreateEditUser.createRoute(
                            "null"
                        )
                    )
                },
                modifier = Modifier
                    .padding(bottom = 60.dp)
                    .align(Alignment.BottomEnd)
                    .padding(16.dp),
            ) {
                Icon(
                    painter = AppTheme.images.post,
                    contentDescription = "Add",
                    tint = AppTheme.colors.white
                )
            }
        }
    }
}
