package com.picpay.desafio.android

class PicPayRepository() {

    private val client = PicPayService.Service

    suspend fun getList() = client.getUsers()
}