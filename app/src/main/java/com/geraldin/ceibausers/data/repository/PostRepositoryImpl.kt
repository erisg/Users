package com.geraldin.ceibausers.data.repository

import com.geraldin.ceibausers.data.models.Post
import com.geraldin.ceibausers.data.remote.UsersApi
import com.geraldin.ceibausers.domain.repository.PostRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PostRepositoryImpl @Inject constructor(
    private val userApi: UsersApi
) : PostRepository {

    override suspend fun getAllPost(id: Int): Flow<List<Post>> {
        return flow {
            emit(userApi.getAllPostByUserId(id))
        }
    }
}
