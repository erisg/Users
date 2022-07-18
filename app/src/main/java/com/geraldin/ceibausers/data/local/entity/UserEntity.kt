package com.geraldin.ceibausers.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = UserEntity.TABLE_NAME)
class UserEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID_COLUMN_NAME)
    var id: Int,

    @ColumnInfo(name = NAME_COLUMN_NAME)
    var name: String,

    @ColumnInfo(name = USERNAME_COLUMN_NAME)
    var username: String,

    @ColumnInfo(name = EMAIL_COLUMN_NAME)
    var email: String,

    @ColumnInfo(name = PHONE_COLUMN_NAME)
    var phone: String,

    @ColumnInfo(name = WEBSITE_COLUMN_NAME)
    var website: String
) {

    companion object {
        const val TABLE_NAME = "USER"
        const val ID_COLUMN_NAME = "ID"
        const val NAME_COLUMN_NAME = "NAME"
        const val USERNAME_COLUMN_NAME = "USERNAME"
        const val EMAIL_COLUMN_NAME = "EMAIL"
        const val PHONE_COLUMN_NAME = "PHONE"
        const val WEBSITE_COLUMN_NAME = "WEBSITE"
    }
}
