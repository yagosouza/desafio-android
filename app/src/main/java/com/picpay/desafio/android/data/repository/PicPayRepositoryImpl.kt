package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.data.api.PicPayService
import com.picpay.desafio.android.data.local.UserDao
import com.picpay.desafio.android.data.local.UserEntity
import com.picpay.desafio.android.data.model.toUser
import com.picpay.desafio.android.domain.exception.NoCachedUsersException
import com.picpay.desafio.android.domain.repository.PicPayRepository

class PicPayRepositoryImpl(
    private val service: PicPayService,
    private val userDao: UserDao
) : PicPayRepository {

    override suspend fun getUsers(): List<User> {
        return try {
            val userListResponse = service.getUsers()

            saveLocalUsers(userListResponse.map { it.toUser() })

            userListResponse.map {
                it.toUser()
            }

        } catch (e: Exception) {
            if (getLocalUsers().isNullOrEmpty()) {
                throw NoCachedUsersException()
            }
            getLocalUsers()
        }
    }

    override suspend fun getLocalUsers(): List<User> {
        return userDao.getUsers().map {
            User(
                img = it.img,
                name = it.name,
                id = it.idUser,
                username = it.userName
            )
        }

    }

    override suspend fun saveLocalUsers(userList: List<User>) {
        userList.forEach {
            userDao.saveUser(UserEntity(it.id, it.name, it.username, it.img))
        }
    }
}