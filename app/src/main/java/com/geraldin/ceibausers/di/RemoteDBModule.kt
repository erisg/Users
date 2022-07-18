package com.geraldin.ceibausers.di

import com.geraldin.ceibausers.data.remote.UsersApi
import com.geraldin.ceibausers.data.repository.PostRepositoryImpl
import com.geraldin.ceibausers.domain.repository.PostRepository
import com.geraldin.ceibausers.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object RemoteDBModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiClient(retrofit: Retrofit): UsersApi {
        return retrofit.create(UsersApi::class.java)
    }

    @Singleton
    @Provides
    fun providePostRepository(api: UsersApi): PostRepository {
        return PostRepositoryImpl(api)
    }
}
