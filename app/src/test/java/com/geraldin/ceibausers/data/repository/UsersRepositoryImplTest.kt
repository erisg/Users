package com.geraldin.ceibausers.data.repository

import com.geraldin.ceibausers.data.local.dao.UserDao
import com.geraldin.ceibausers.data.local.entity.UserEntity
import com.geraldin.ceibausers.data.models.UserResponse
import com.geraldin.ceibausers.data.remote.UsersApi
import com.geraldin.ceibausers.util.Mapper
import io.mockk.called
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class UsersRepositoryImplTest {
    private val userApi = mockk<UsersApi>()
    private val userDao = mockk<UserDao>()
    private val mapper = mockk<Mapper>(relaxed = true)
    lateinit var usersRepositoryImpl: UsersRepositoryImpl

    @Before
    fun setup() {
        usersRepositoryImpl = UsersRepositoryImpl(
            userApi,
            userDao,
            mapper
        )
    }

    @Test
    fun getAllUsersFromRemoteDB() = runBlocking {
        // preparation
        val post = mockk<UserResponse>()
        coEvery {
            userApi.getAllUser()
        } returns listOf(post)

        // execution

        usersRepositoryImpl.getAllUser()

        // verification

        coVerify(exactly = 0) {
            userApi.getAllUser() wasNot called
        }

        confirmVerified(post)
    }

    @Test
    fun getAllUsersFromLocalDB() = runBlocking {
        // preparation
        val users = mockk<UserEntity>()
        coEvery {
            userDao.getAllMovies()
        } returns listOf(users)

        // execution

        usersRepositoryImpl.getAllUser()

        // verification

        coVerify(exactly = 0) {
            userDao.getAllMovies() wasNot called
        }

        confirmVerified(users)
    }

    @Test
    fun saveInfoLocal() = runBlocking {
        val usersEntity = mockk<UserEntity>()
        val usersResponse = mockk<UserResponse>()
        every {
            mapper.userToUserEntity(listOf(usersResponse))
        } returns listOf(usersEntity)

        coEvery {
            userDao.insertMovie(listOf(usersEntity))
        } returns Unit

        usersRepositoryImpl.saveInfoLocal(listOf(usersResponse))

        verify {
            mapper.userToUserEntity(listOf(usersResponse))
        }

        coVerify {
            userDao.insertMovie(listOf(usersEntity))
        }
    }

    @Test
    fun searchUser() = runBlocking {
        val usersEntity = mockk<UserEntity>()

        coEvery {
            userDao.getFilteredUser("a")
        } returns listOf(usersEntity)

        usersRepositoryImpl.searchUser("a")

        coVerify(exactly = 0) {
            userDao.getFilteredUser("a") wasNot called
        }

        confirmVerified(usersEntity)
    }

    @After
    fun teardown() {
        confirmVerified(
            userApi,
            userDao,
            mapper
        )
    }
}
