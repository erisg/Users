package com.geraldin.ceibausers.util

import com.geraldin.ceibausers.data.local.entity.UserEntity
import com.geraldin.ceibausers.data.models.User
import com.geraldin.ceibausers.data.models.User as UserDomain
import com.geraldin.ceibausers.data.models.UserResponse
import javax.inject.Inject

class Mapper @Inject constructor() {

    fun userDataToUserDomain(usersResponse: List<User>): List<UserDomain> {
        return usersResponse.map { userResponse ->
            with(userResponse) {
                UserDomain(
                    id,
                    name,
                    username,
                    email,
                    phone,
                    website
                )
            }
        }
    }

    fun userEntityToUser(users: List<UserEntity>): List<User> {
        return users.map { user ->
            with(user) {
                User(
                    id,
                    name,
                    username,
                    email,
                    phone,
                    website
                )
            }
        }
    }

    fun userToUserEntity(users: List<UserResponse>): List<UserEntity> {
        return users.map { user ->
            with(user) {
                UserEntity(
                    id,
                    name,
                    username,
                    email,
                    phone,
                    website
                )
            }
        }
    }
}
