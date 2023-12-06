package com.graphql.task.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.graphql.test.UserListQuery
import com.graphql.test.UsersDetailsQuery
import com.graphql.task.domain.Result
import com.graphql.task.domain.usecase.usercase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userUseCase: UserUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState = _uiState as StateFlow<UiState>
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO

    fun getUsers() {
        viewModelScope.launch(dispatcher) {
            when (val result = userUseCase.userList()) {
                is Result.Success -> {
                    _uiState.emit(UiState.LoadedUsers(result.data.data!!))
                }

                is Result.Error -> {
                    _uiState.emit(UiState.Error(result.errorMsg))
                }
            }
        }
    }


    fun getUserDetails(userId:String) {
        viewModelScope.launch(dispatcher) {
            when (val result = userUseCase.userDetails(userId)) {
                is Result.Success -> {
                    _uiState.emit(UiState.LoadedDetails(result.data))
                }

                is Result.Error -> {
                    _uiState.emit(UiState.Error(result.errorMsg))
                }
            }
        }
    }


    sealed class UiState {
        data object Loading : UiState()
        data class Error(val message: String) : UiState()
        data class LoadedUsers(val users: List<UserListQuery.Data1?>?) : UiState()
        data class LoadedDetails(val userDetail: UsersDetailsQuery.User) : UiState()
    }
}