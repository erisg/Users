package com.geraldin.ceibausers.domain.repository

import com.geraldin.ceibausers.data.models.Post
import kotlinx.coroutines.flow.Flow

interface PostRepository {

    suspend fun getAllPost(id: Int): Flow<List<Post>>
}
