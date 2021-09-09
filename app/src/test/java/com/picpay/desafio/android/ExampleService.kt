package com.picpay.desafio.android

import com.picpay.desafio.android.data.model.User
import com.picpay.desafio.android.data.service.PicPayServiceApi
import kotlinx.coroutines.runBlocking

class ExampleService(
    private val serviceApi: PicPayServiceApi
) {

    fun example(): List<User> = runBlocking {
        val users = serviceApi.getUsers()
        return@runBlocking users.body() ?: emptyList()
    }
}