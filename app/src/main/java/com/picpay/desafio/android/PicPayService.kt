package com.picpay.desafio.android

import retrofit2.http.GET

interface PicPayService {

    @GET("users")
    suspend fun getUsers(): List<User>

    companion object {
        val Service: PicPayService by lazy {
            NetworkUtils.getRetrofit().create(PicPayService::class.java)
        }
    }
}