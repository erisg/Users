package com.geraldin.ceibausers.di

import android.app.Application
import com.geraldin.ceibausers.data.local.UserDataBase
import com.geraldin.ceibausers.data.local.dao.UserDao
import com.geraldin.ceibausers.data.remote.UsersApi
import com.geraldin.ceibausers.data.repository.UsersRepositoryImpl
import com.geraldin.ceibausers.domain.repository.UserRepository
import com.geraldin.ceibausers.util.Mapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDBModule {

    @Provides
    @Singleton
    fun getAppDatabase(context: Application): UserDataBase {
        return UserDataBase.getAppDBInstance(context)
    }

    @Provides
    @Singleton
    fun getAppDao(appDatabase: UserDataBase): UserDao {
        return appDatabase.getUserDao()
    }

    @Singleton
    @Provides
    fun provideUserRepository(api: UsersApi, userDao: UserDao, mapper: Mapper): UserRepository {
        return UsersRepositoryImpl(api, userDao, mapper)
    }
}
