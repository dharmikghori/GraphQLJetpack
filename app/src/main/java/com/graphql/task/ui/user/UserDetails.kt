package com.graphql.task.ui.user

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.graphql.test.R
import com.graphql.task.ui.composables.PrimaryText
import com.graphql.task.user.UserViewModel

@Composable
fun UserDetails(navController: NavController, userId: String) {
    val viewModel = hiltViewModel<UserViewModel>()
    val uiState = viewModel.uiState.collectAsState()
    LaunchedEffect(key1 = 1) {
        viewModel.getUserDetails(userId)
    }
    Column {

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_keyboard_backspace_24),
                tint = Color.DarkGray,
                contentDescription = stringResource(id = R.string.user_details),
                modifier = Modifier
                    .clickable {
                        navController.popBackStack()
                    }
                    .padding(10.dp)
            )
            PrimaryText(text = stringResource(R.string.user_details))
        }
        Box(
            Modifier
                .fillMaxSize()
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

                is UserViewModel.UiState.LoadedDetails -> {
                    val userData = state.userDetail
                    PrimaryText(text = userData.username.toString())
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
}