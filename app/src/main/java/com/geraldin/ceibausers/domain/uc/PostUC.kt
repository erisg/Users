package com.geraldin.ceibausers.domain.uc

import com.geraldin.ceibausers.data.models.Post
import com.geraldin.ceibausers.domain.repository.PostRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class PostUC @Inject constructor(
    private val postRepository: PostRepository
) {

    suspend fun getAllPost(id: Int): Flow<List<Post>> {
        return postRepository.getAllPost(id)
    }
}
