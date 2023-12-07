package com.graphql.task.ui.user

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.graphql.task.theme.AppTheme
import com.graphql.task.ui.composables.PrimaryButton
import com.graphql.task.ui.composables.PrimaryText
import com.graphql.task.user.UserViewModel
import com.graphql.test.R
import com.graphql.test.UsersDetailsQuery

@Composable
fun CreateEditUser(navController: NavController, userInfo: UsersDetailsQuery.User?) {
    val viewModel = hiltViewModel<UserViewModel>()
    val uiState = viewModel.uiState.collectAsState()

    Column {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(AppTheme.colors.fillSecondary)
        ) {
            Icon(
                painter = AppTheme.images.backArrow,
                tint = Color.White,
                contentDescription = stringResource(id = R.string.user_details),
                modifier = Modifier
                    .clickable {
                        navController.popBackStack()
                    }
                    .padding(10.dp)
            )
            PrimaryText(
                color = AppTheme.colors.white,
                style = AppTheme.typography.semiBoldMontserrat20,
                text = stringResource(R.string.user_details)
            )
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
                    Column() {
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            PrimaryText(
                                style = AppTheme.typography.regularMontserrat16,
                                text = stringResource(R.string.full_name)
                            )

                            Spacer(modifier = Modifier.width(10.dp))

                            PrimaryText(
                                style = AppTheme.typography.regularMontserrat14,
                                text = userData.username.toString()
                            )
                        }

                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            PrimaryText(
                                style = AppTheme.typography.regularMontserrat16,
                                text = stringResource(R.string.email)
                            )

                            Spacer(modifier = Modifier.width(10.dp))

                            PrimaryText(
                                style = AppTheme.typography.regularMontserrat14,
                                text = userData.email.toString()
                            )
                        }

                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            PrimaryText(
                                style = AppTheme.typography.regularMontserrat16,
                                text = stringResource(R.string.website)
                            )

                            Spacer(modifier = Modifier.width(10.dp))

                            PrimaryText(
                                style = AppTheme.typography.regularMontserrat14,
                                text = userData.website.toString()
                            )
                        }

                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            PrimaryText(
                                style = AppTheme.typography.regularMontserrat16,
                                text = stringResource(R.string.phone)
                            )

                            Spacer(modifier = Modifier.width(10.dp))

                            PrimaryText(
                                style = AppTheme.typography.regularMontserrat14,
                                text = userData.phone.toString()
                            )
                        }

                        PrimaryButton(
                            onClick = { }, text = stringResource(
                                R.string.edit_user
                            )
                        )
                    }
                }

                is UserViewModel.UiState.Error -> {
                    val errorMsg = state.message
                    Toast.makeText(LocalContext.current, errorMsg, Toast.LENGTH_LONG).show()
                    PrimaryText(style = AppTheme.typography.regularMontserrat14, text = errorMsg)
                }

                else -> {}
            }
        }
    }
}