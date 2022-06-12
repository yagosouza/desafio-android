package com.picpay.desafio.android.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UserData")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    var idUser: Int = 0,
    @ColumnInfo
    var name: String,
    @ColumnInfo
    var userName: String,
    @ColumnInfo
    var img: String
)