package com.geraldin.ceibausers.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.geraldin.ceibausers.data.local.entity.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: List<UserEntity>)

    @Query("SELECT * FROM USER")
    suspend fun getAllMovies(): List<UserEntity>

    @Query(
        "SELECT * FROM ${UserEntity.TABLE_NAME} " +
            "WHERE ${UserEntity.NAME_COLUMN_NAME} LIKE '%' || :filterName || '%' "
    )
    suspend fun getFilteredUser(filterName: String): List<UserEntity>
}
