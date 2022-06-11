package com.picpay.desafio.android.data

import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.data.api.PicPayService
import com.picpay.desafio.android.data.local.UserDao
import com.picpay.desafio.android.data.local.UserEntity
import com.picpay.desafio.android.data.model.toUser
import com.picpay.desafio.android.helper.network.Output
import com.picpay.desafio.android.helper.network.parseResponse

class PicPayRepositoryImpl(
    private val service: PicPayService,
    private val userDao: UserDao
) : PicPayRepository {

   // private val userDao: UserDao? = null

    override suspend fun getUsers(): List<User> {
        val result = service.getUsers().parseResponse()

        return when (result) {
            is Output.Success -> {
                val userResponseList = result.value

                saveLocalUsers(userResponseList.map { it.toUser() })

                userResponseList.map {
                    it.toUser()
                }
            }
            is Output.Failure -> {
                if (getLocalUsers().isNullOrEmpty()) {
                    throw GetUsersException()
                } else
                    getLocalUsers()
            }
        }
    }

    override suspend fun getLocalUsers(): List<User> {
        val usersCasted = arrayListOf<User>()
        val localUsers = userDao?.getUsers()
        localUsers?.forEach {
            usersCasted.add(User(it.img, it.name, it.idUser, it.userName))
        }
        return usersCasted
    }

    override suspend fun saveLocalUsers(userList: List<User>) {
        userList.forEach {
            userDao?.saveUser(UserEntity(0, it.name, it.username, it.img))
        }
    }
}

interface PicPayRepository {
    suspend fun getUsers(): List<User>
    suspend fun getLocalUsers(): List<User>
    suspend fun saveLocalUsers(userList: List<User>)
}

class GetUsersException : Exception()