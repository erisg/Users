package com.geraldin.ceibausers.domain

import com.geraldin.ceibausers.data.models.Post
import com.geraldin.ceibausers.domain.repository.PostRepository
import com.geraldin.ceibausers.domain.uc.PostUC
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class PostUCTest {
    private val postRepository = mockk<PostRepository>()
    lateinit var postUC: PostUC

    @Before
    fun setup() {
        postUC = PostUC(postRepository)
    }

    @Test
    fun getAllPost() = runBlocking {
        val post = mockk<Post>()

        coEvery {
            postRepository.getAllPost(1)
        } returns flow { listOf(post) }

        postUC.getAllPost(1)

        coVerify {
            postRepository.getAllPost(1)
        }

        confirmVerified(post)
    }

    @After
    fun teardown() {
        confirmVerified(postRepository)
    }
}
