package com.geraldin.ceibausers.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.geraldin.ceibausers.data.local.dao.UserDao
import com.geraldin.ceibausers.data.local.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class UserDataBase : RoomDatabase() {

    abstract fun getUserDao(): UserDao

    companion object {
        private var DB_INSTANCE: UserDataBase? = null

        fun getAppDBInstance(context: Context): UserDataBase {
            if (DB_INSTANCE == null) {
                DB_INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    UserDataBase::class.java,
                    "APP_DB"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return DB_INSTANCE!!
        }
    }
}
