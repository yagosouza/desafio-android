package com.picpay.desafio.android

import android.app.Application
import androidx.room.Room
import com.picpay.desafio.android.domain.repository.PicPayRepository
import com.picpay.desafio.android.data.repository.PicPayRepositoryImpl
import com.picpay.desafio.android.data.api.PicPayService
import com.picpay.desafio.android.data.local.AppDatabase
import com.picpay.desafio.android.data.local.UserDao
import com.picpay.desafio.android.domain.usecase.GetUsersUseCase
import com.picpay.desafio.android.helper.network.Service
import com.picpay.desafio.android.presentation.PicPayViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val databaseModule = module {
    fun provideDataBase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "database_picpay")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideDao(dataBase: AppDatabase): UserDao {
        return dataBase.userDao()
    }
    single { provideDataBase(androidApplication()) }
    single { provideDao(get()) }
}

val picPayModule = module {

    single { Service().createService(PicPayService::class.java) }

    single<PicPayRepository> { PicPayRepositoryImpl(get(), get()) }

    viewModel { PicPayViewModel(GetUsersUseCase(get())) }
}
