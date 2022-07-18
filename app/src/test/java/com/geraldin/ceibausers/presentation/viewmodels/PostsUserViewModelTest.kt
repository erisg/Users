package com.geraldin.ceibausers.presentation.viewmodels

import com.geraldin.ceibausers.data.models.Post
import com.geraldin.ceibausers.domain.uc.PostUC
import com.geraldin.ceibausers.presentation.states.PostState
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

class PostsUserViewModelTest {
    private val postUC = mockk<PostUC>()
    private var postState = mockk<PostState>()
    lateinit var postsUserViewModel: PostsUserViewModel
    val dispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        postsUserViewModel = PostsUserViewModel(postUC)
    }

    @Test
    fun loadUserPostSuccess() = runBlocking {
        val post = mockk<Post>()

        coEvery {
            postUC.getAllPost(1)
        } returns flow { listOf(post) }

        postsUserViewModel.loadUserPost(1)

        coVerify {
            postUC.getAllPost(1)
            postState = PostState.Success(listOf(post))
        }

        confirmVerified(post)
    }

    @Test
    fun loadUserPostError() = runBlocking {
        val post = mockk<Post>()

        coEvery {
            postUC.getAllPost(1)
        } returns flow { listOf(post) }

        postsUserViewModel.loadUserPost(1)

        coVerify {
            postUC.getAllPost(1)
            postState = PostState.Error(Throwable(this.toString()))
        }

        confirmVerified(post)
    }

    @After
    fun teardown() {
        Dispatchers.resetMain()
        confirmVerified(postUC)
    }
}
