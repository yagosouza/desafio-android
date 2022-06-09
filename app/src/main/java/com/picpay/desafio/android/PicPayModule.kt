package com.picpay.desafio.android

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val picPayModule = module {

    factory {
        PicPayRepository()
    }

    viewModel {
        PicPayViewModel(repository = get())
    }
}
