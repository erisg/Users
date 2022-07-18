package com.geraldin.ceibausers.presentation.viewmodels

import com.geraldin.ceibausers.data.models.User
import com.geraldin.ceibausers.domain.uc.UserUC
import com.geraldin.ceibausers.presentation.states.UsersState
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class UserViewModelTest {
    private val userUC = mockk<UserUC>()
    private var userState = mockk<UsersState>()
    lateinit var userViewModel: UserViewModel
    val dispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        userViewModel = UserViewModel(userUC)
    }

    @Test
    fun loadUserSuccess() = runBlocking {
        val user = mockk<User>()

        coEvery {
            userUC.getAllUser()
        } returns flow { listOf(user) }

        userViewModel.loadUsers()

        coVerify {
            userUC.getAllUser()
            userState = UsersState.Success(listOf(user))
        }

        confirmVerified(user)
    }

    @Test
    fun loadUserError() = runBlocking {
        val user = mockk<User>()

        coEvery {
            userUC.getAllUser()
        } returns flow { listOf(user) }

        userViewModel.loadUsers()

        coVerify {
            userUC.getAllUser()
            userState = UsersState.Error(Throwable(this.toString()))
        }

        confirmVerified(user)
    }

    @Test
    fun searchUsersSuccess() = runBlocking {
        val user = mockk<User>()

        coEvery {
            userUC.searchUsers("a")
        } returns flow { listOf(user) }

        userViewModel.searchUsers("a")

        coVerify {
            userUC.searchUsers("a")
            userState = UsersState.Success(listOf(user))
        }

        confirmVerified(user)
    }

    @Test
    fun searchUsersError() = runBlocking {
        val user = mockk<User>()

        coEvery {
            userUC.searchUsers("a")
        } returns flow { listOf(user) }

        userViewModel.searchUsers("a")

        coVerify {
            userUC.searchUsers("a")
            userState = UsersState.Error(Throwable(this.toString()))
        }

        confirmVerified(user)
    }

    @After
    fun teardown() {
        Dispatchers.resetMain()
        confirmVerified(userUC)
    }
}
