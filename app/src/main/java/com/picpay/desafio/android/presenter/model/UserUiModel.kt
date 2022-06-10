package com.picpay.desafio.android.presenter.model

import com.picpay.desafio.android.domain.model.User

class UserUiModel(
    val img: String,
    val name: String,
    val id: Int,
    val username: String
)

fun User.toUiModel() = UserUiModel(
    img = this.img,
    name = this.name,
    id = this.id,
    username = this.username
)