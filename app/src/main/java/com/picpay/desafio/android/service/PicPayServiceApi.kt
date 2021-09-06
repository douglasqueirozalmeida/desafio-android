package com.picpay.desafio.android.service

import com.picpay.desafio.android.model.User
import retrofit2.Response
import retrofit2.http.GET


interface PicPayServiceApi {

    @GET("users")
    suspend fun getUsers(): Response<List<User>?>
}