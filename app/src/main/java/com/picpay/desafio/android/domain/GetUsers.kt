package com.picpay.desafio.android.domain

import com.picpay.desafio.android.data.PicPayRepository
import com.picpay.desafio.android.domain.model.User
import java.lang.Exception

class GetUsers(
    private val picPayRepository: PicPayRepository
) : GetUsersUseCase {

    override suspend fun invoke(): List<User> = //try {
        picPayRepository.getUsers()
   /* } catch (ex: Exception) {
        listOf()
    }*/
}

interface GetUsersUseCase {
    suspend operator fun invoke(): List<User>
}