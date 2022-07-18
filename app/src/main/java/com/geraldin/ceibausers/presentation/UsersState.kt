package com.geraldin.ceibausers.presentation

import com.geraldin.ceibausers.data.models.User

sealed class UsersState {
    data class Success(var users: List<User>) : UsersState()
    data class Error(var exception: Throwable) : UsersState()
}
