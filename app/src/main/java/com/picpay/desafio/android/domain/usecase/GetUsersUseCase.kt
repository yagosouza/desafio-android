package com.picpay.desafio.android.domain.usecase

import com.picpay.desafio.android.domain.repository.PicPayRepository

class GetUsersUseCase(
    private val picPayRepository: PicPayRepository
) {
    suspend operator fun invoke() =
        picPayRepository.getUsers()
}
