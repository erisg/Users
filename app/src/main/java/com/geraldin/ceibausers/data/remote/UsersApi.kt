package com.geraldin.ceibausers.data.remote

import com.geraldin.ceibausers.data.models.Post
import com.geraldin.ceibausers.data.models.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UsersApi {

    @GET("users")
    suspend fun getAllUser(): List<UserResponse>

    @GET("posts")
    suspend fun getAllPostByUserId(
        @Query("userId") id: Int
    ): List<Post>
}
