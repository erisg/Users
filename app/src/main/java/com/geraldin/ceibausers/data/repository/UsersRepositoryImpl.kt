package com.geraldin.ceibausers.data.repository

import com.geraldin.ceibausers.data.local.dao.UserDao
import com.geraldin.ceibausers.data.models.User
import com.geraldin.ceibausers.data.models.UserResponse
import com.geraldin.ceibausers.data.remote.UsersApi
import com.geraldin.ceibausers.domain.repository.UserRepository
import com.geraldin.ceibausers.util.Mapper
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UsersRepositoryImpl @Inject constructor(
    private val userApi: UsersApi,
    private val userDao: UserDao,
    private val mapper: Mapper
) : UserRepository {

    override suspend fun getAllUser(): Flow<List<User>> {
        return flow {
            if (mapper.userEntityToUser(userDao.getAllMovies()).isEmpty()) {
                getAllUserFromApi()
            }
            emit(mapper.userEntityToUser(userDao.getAllMovies()))
        }
    }

    override suspend fun saveInfoLocal(users: List<UserResponse>) {
        userDao.insertMovie(mapper.userToUserEntity(users))
    }

    override suspend fun getAllUserFromApi() {
        saveInfoLocal(userApi.getAllUser())
    }

    override suspend fun searchUser(value: String): Flow<List<User>> {
        return flow {
            emit(mapper.userEntityToUser(userDao.getFilteredUser(value)))
        }
    }
}
