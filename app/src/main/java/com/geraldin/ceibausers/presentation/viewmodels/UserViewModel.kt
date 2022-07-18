package com.geraldin.ceibausers.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geraldin.ceibausers.domain.uc.UserUC
import com.geraldin.ceibausers.presentation.UsersState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

@HiltViewModel
class UserViewModel @Inject constructor(private val userUC: UserUC) : ViewModel() {

    private val _usersState: MutableStateFlow<UsersState> = MutableStateFlow(
        UsersState.Success(emptyList())
    )
    val usersState: StateFlow<UsersState> = _usersState

    fun loadUsers() {
        viewModelScope.launch {
            userUC.getAllUser()
                .catch {
                    _usersState.value = UsersState.Error(Throwable(this.toString()))
                }
                .collect { result ->
                    _usersState.value = UsersState.Success(result)
                }
        }
    }
}
