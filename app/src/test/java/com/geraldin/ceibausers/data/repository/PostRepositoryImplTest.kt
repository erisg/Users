package com.geraldin.ceibausers.data.repository

import com.geraldin.ceibausers.data.models.Post
import com.geraldin.ceibausers.data.remote.UsersApi
import io.mockk.called
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class PostRepositoryImplTest {

    private val usersApi = mockk<UsersApi>()
    lateinit var postRepositoryImpl: PostRepositoryImpl

    @Before
    fun setup() {
        postRepositoryImpl = PostRepositoryImpl(usersApi)
    }

    @Test
    fun getAllPost() = runBlocking {
        // preparation
        val post = mockk<Post>()

        coEvery {
            usersApi.getAllPostByUserId(1)
        } returns listOf(post)

        // execution
        postRepositoryImpl.getAllPost(1)

        coVerify(exactly = 0) {
            usersApi.getAllPostByUserId(1) wasNot called
        }
    }

    @After
    fun teardown() {
        confirmVerified(usersApi)
    }
}
