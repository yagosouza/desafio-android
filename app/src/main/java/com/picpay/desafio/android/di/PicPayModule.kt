package com.picpay.desafio.android

import com.picpay.desafio.android.data.PicPayRepository
import com.picpay.desafio.android.data.PicPayRepositoryImpl
import com.picpay.desafio.android.data.api.PicPayService
import com.picpay.desafio.android.domain.GetUsers
import com.picpay.desafio.android.domain.GetUsersUseCase
import com.picpay.desafio.android.helper.network.Service
import com.picpay.desafio.android.presenter.PicPayViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val picPayModule = module {

    single { Service().createService(PicPayService::class.java) }

    single<PicPayRepository> { PicPayRepositoryImpl(get()) }

    single<GetUsersUseCase> { GetUsers(get()) }

    single { PicPayViewModel(get()) }
}
