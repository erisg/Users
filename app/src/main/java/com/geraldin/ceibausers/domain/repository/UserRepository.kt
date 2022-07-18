package com.geraldin.ceibausers.domain.repository

import com.geraldin.ceibausers.data.models.User
import com.geraldin.ceibausers.data.models.UserResponse
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun getAllUser(): Flow<List<User>>
    suspend fun saveInfoLocal(users: List<UserResponse>)
    suspend fun getAllUserFromApi()
}
