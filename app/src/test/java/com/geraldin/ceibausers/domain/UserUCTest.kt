package com.geraldin.ceibausers.domain

import com.geraldin.ceibausers.data.models.Post
import com.geraldin.ceibausers.data.models.User
import com.geraldin.ceibausers.data.repository.UsersRepositoryImpl
import com.geraldin.ceibausers.domain.repository.UserRepository
import com.geraldin.ceibausers.domain.uc.PostUC
import com.geraldin.ceibausers.domain.uc.UserUC
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class UserUCTest {
    private val userRepository = mockk<UserRepository>()
    lateinit var userUC: UserUC

    @Before
    fun setup() {
        userUC = UserUC(userRepository)
    }

    @Test
    fun getAllUsers() = runBlocking {
        val user = mockk<User>()

        coEvery {
            userRepository.getAllUser()
        } returns flow { listOf(user) }

        userUC.getAllUser()

        coVerify {
            userRepository.getAllUser()
        }

        confirmVerified(user)
    }

    @Test
    fun searchUsers() = runBlocking {
        val user = mockk<User>()

        coEvery {
            userRepository.searchUser("a")
        } returns flow { listOf(user) }

        userUC.searchUsers("a")

        coVerify {
            userRepository.searchUser("a")
        }

        confirmVerified(user)
    }

    @After
    fun teardown() {
        confirmVerified(userRepository)
    }
}
