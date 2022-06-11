package com.picpay.desafio.android

import androidx.room.Room
import com.picpay.desafio.android.data.PicPayRepository
import com.picpay.desafio.android.data.PicPayRepositoryImpl
import com.picpay.desafio.android.data.api.PicPayService
import com.picpay.desafio.android.data.local.AppDatabase
import com.picpay.desafio.android.domain.GetUsers
import com.picpay.desafio.android.domain.GetUsersUseCase
import com.picpay.desafio.android.helper.network.Service
import com.picpay.desafio.android.presenter.PicPayViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val picPayModule = module {

    single { Service().createService(PicPayService::class.java) }

    single { Room.databaseBuilder(
        androidApplication(),
        AppDatabase::class.java,
        "database-picpay")
        .build()}

//    single<UserDao> {
//        val database = get<AppDatabase>()
//        database.userDao()
//    }
    single { get<AppDatabase>().userDao() }

    single<PicPayRepository> { PicPayRepositoryImpl(get(), get()) }

    single<GetUsersUseCase> { GetUsers(get()) }

    viewModel { PicPayViewModel(get()) }
}
