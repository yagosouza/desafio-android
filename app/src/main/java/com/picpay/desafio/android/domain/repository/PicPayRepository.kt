package com.picpay.desafio.android.domain.repository

import com.picpay.desafio.android.data.model.UserResponse
import com.picpay.desafio.android.domain.model.User

interface PicPayRepository {
    suspend fun getUsers(): List<User>
    suspend fun getLocalUsers(): List<User>
    suspend fun saveLocalUsers(userList: List<User>)
}
