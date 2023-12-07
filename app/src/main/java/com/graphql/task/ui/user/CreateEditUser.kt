package com.graphql.task.ui.user

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import com.graphql.task.theme.AppTheme
import com.graphql.task.ui.composables.PrimaryButton
import com.graphql.task.ui.composables.PrimaryText
import com.graphql.task.ui.showToast
import com.graphql.task.user.UserViewModel
import com.graphql.test.CreateUserMutation
import com.graphql.test.R
import com.graphql.test.UpdateUserMutation
import com.graphql.test.UsersDetailsQuery

@Composable
fun CreateEditUser(navController: NavController, userInfo: UsersDetailsQuery.User?) {
    val viewModel = hiltViewModel<UserViewModel>()
    val uiState = viewModel.uiState.collectAsState()
    var fullName: String by remember { mutableStateOf(userInfo?.name ?: "") }
    var userName: String by remember { mutableStateOf(userInfo?.username ?: "") }
    var email: String by remember { mutableStateOf(userInfo?.email ?: "") }
    var website: String by remember { mutableStateOf(userInfo?.website ?: "") }
    var phone: String by remember { mutableStateOf(userInfo?.phone ?: "") }

    var isCreateOrUpdates: Boolean by remember {
        mutableStateOf(true)
    }

    when (uiState.value) {
        is UserViewModel.UiState.UpdateUser -> {
            if (isCreateOrUpdates) {
                isCreateOrUpdates = false
                LocalContext.current.showToast(stringResource(R.string.user_updated))
                navController.popBackStack()
            }
        }

        is UserViewModel.UiState.CreateUser -> {
            if (isCreateOrUpdates) {
                isCreateOrUpdates = false
                LocalContext.current.showToast(stringResource(R.string.user_created))
                navController.popBackStack()
            }
        }

        else -> {
        }
    }
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
                contentDescription = stringResource(
                    id = if (userInfo != null) {
                        R.string.edit_user
                    } else {
                        R.string.create_user
                    }
                ),
                modifier = Modifier
                    .clickable {
                        navController.popBackStack()
                    }
                    .padding(10.dp)
            )
            PrimaryText(
                color = AppTheme.colors.white,
                style = AppTheme.typography.semiBoldMontserrat20,
                text = stringResource(
                    id = if (userInfo != null) {
                        R.string.edit_user
                    } else {
                        R.string.create_user
                    }
                )
            )
        }
        Box(
            Modifier
                .fillMaxSize()
        ) {


            Column(Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    PrimaryText(
                        style = AppTheme.typography.regularMontserrat16,
                        text = stringResource(R.string.full_name)
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        singleLine = true,
                        value = fullName,
                        textStyle = AppTheme.typography.regularMontserrat16,
                        onValueChange = { fullName = it })
                }

                Column(
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    PrimaryText(
                        style = AppTheme.typography.regularMontserrat16,
                        text = stringResource(R.string.user_name)
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        singleLine = true,
                        value = userName,
                        textStyle = AppTheme.typography.regularMontserrat16,
                        onValueChange = { userName = it })
                }

                Column(
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    PrimaryText(
                        style = AppTheme.typography.regularMontserrat16,
                        text = stringResource(R.string.email)
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        singleLine = true,
                        value = email,
                        textStyle = AppTheme.typography.regularMontserrat16,
                        onValueChange = { email = it })
                }

                Column(
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    PrimaryText(
                        style = AppTheme.typography.regularMontserrat16,
                        text = stringResource(R.string.website)
                    )
                    Spacer(modifier = Modifier.height(5.dp))

                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        singleLine = true,
                        value = website,
                        textStyle = AppTheme.typography.regularMontserrat16,
                        onValueChange = { website = it })
                }

                Column(
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    PrimaryText(
                        style = AppTheme.typography.regularMontserrat16,
                        text = stringResource(R.string.phone)
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        singleLine = true,
                        value = phone,
                        textStyle = AppTheme.typography.regularMontserrat16,
                        onValueChange = { phone = it })
                }

                PrimaryButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    onClick = {
                        if (checkValidation()) {
                            if (userInfo == null) {
                                viewModel.createUser(
                                    CreateUserMutation.CreateUser(
                                        "",
                                        name = fullName,
                                        username = userName,
                                        email = email,
                                        phone = phone,
                                        website = website
                                    )
                                )
                            } else {
                                viewModel.updateUser(
                                    UpdateUserMutation.UpdateUser(
                                        id = userInfo.id,
                                        name = fullName,
                                        username = userName,
                                        email = email,
                                        phone = phone,
                                        website = website
                                    )
                                )
                            }
                        }
                    }, text = stringResource(
                        R.string.save
                    )
                )
            }
        }
    }
}

fun checkValidation(): Boolean {
    return true
}
