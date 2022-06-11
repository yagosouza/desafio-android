package com.picpay.desafio.android.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Insert
    suspend fun saveUser(userEntity: UserEntity)

    @Query("SELECT * FROM UserData WHERE idUser = :idUser")
    suspend fun getUserById(idUser: Int): UserEntity

    @Query("SELECT * FROM UserData")
    suspend fun getUsers(): List<UserEntity>

    @Query("DELETE FROM UserData WHERE idUser = :idUser")
    suspend fun deleteUser(idUser: Int)
}