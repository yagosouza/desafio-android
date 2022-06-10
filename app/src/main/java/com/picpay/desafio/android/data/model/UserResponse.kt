package com.picpay.desafio.android.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.picpay.desafio.android.domain.model.User
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserResponse(
    @SerializedName("img") val img: String,
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: Int,
    @SerializedName("username") val username: String
) : Parcelable

fun UserResponse.toUser() = User(
    img = this.img,
    name = this.name,
    id = this.id,
    username = this.username
)