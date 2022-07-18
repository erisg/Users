package com.geraldin.ceibausers.domain.uc

import com.geraldin.ceibausers.data.models.User
import com.geraldin.ceibausers.domain.repository.UserRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class UserUC @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend fun getAllUser(): Flow<List<User>> {
        return userRepository.getAllUser()
    }
}
