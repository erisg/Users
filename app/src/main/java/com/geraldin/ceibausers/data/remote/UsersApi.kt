package com.geraldin.ceibausers.data.remote

import com.geraldin.ceibausers.data.models.UserResponse
import retrofit2.http.GET

interface UsersApi {

    @GET("/users")
    suspend fun getAllUser(): List<UserResponse>
}
