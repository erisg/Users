package com.geraldin.ceibausers.domain.uc

import com.geraldin.ceibausers.data.models.Post
import com.geraldin.ceibausers.data.repository.PostRepositoryImpl
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class PostUC @Inject constructor(
    private val postRepository: PostRepositoryImpl
) {

    suspend fun getAllPost(id: Int): Flow<List<Post>> {
        return postRepository.getAllPost(id)
    }
}
