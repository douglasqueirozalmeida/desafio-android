package com.picpay.desafio.android

import android.provider.Contacts
import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.service.PicPayServiceApi
import kotlinx.coroutines.Dispatchers

class ExampleService(
    private val serviceApi: PicPayServiceApi
) {

    fun example(): List<User> {
            val users = serviceApi.getUsers()
            return users.body() ?: emptyList()
    }
}