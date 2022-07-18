package com.geraldin.ceibausers.presentation.states

import com.geraldin.ceibausers.data.models.Post

sealed class PostState {
    data class Success(var post: List<Post>) : PostState()
    data class Error(var exception: Throwable) : PostState()
}
