package com.picpay.desafio.android.repository

import com.picpay.desafio.android.base.BaseRepository
import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.model.Resource

class UserRepository : BaseRepository() {

    suspend fun getUsers(): Resource<List<User>?> {
        val resposta = serviceApi.getUsers()
        return if (resposta.isSuccessful) {
            Resource.Success(resposta.body())
        } else {
            Resource.Error("falha ao buscar usuario")
        }
    }
}