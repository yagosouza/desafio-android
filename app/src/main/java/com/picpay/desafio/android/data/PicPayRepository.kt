package com.picpay.desafio.android.data

import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.data.api.PicPayService
import com.picpay.desafio.android.data.model.toUser
import com.picpay.desafio.android.helper.network.Output
import com.picpay.desafio.android.helper.network.parseResponse

class PicPayRepositoryImpl(
    private val service: PicPayService
) : PicPayRepository{

    override suspend fun getUsers(): List<User> {
        val result = service.getUsers().parseResponse()

        return when (result) {
            is Output.Success -> {
                val userResponseList = result.value

                userResponseList.map {
                    it.toUser()
                }
            }
            is Output.Failure -> throw GetUsersException()
        }
    }
}

interface PicPayRepository {
    suspend fun getUsers(): List<User>
}

class GetUsersException : Exception()