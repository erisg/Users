package com.geraldin.ceibausers.data.remote

import com.geraldin.ceibausers.data.models.Post
import com.geraldin.ceibausers.data.models.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface UsersApi {

    @GET("users")
    suspend fun getAllUser(): List<UserResponse>

    @GET("posts/userId/{id}")
    suspend fun getAllPostByUser(
        @Path("id") id: Int
    ): List<Post>
}
