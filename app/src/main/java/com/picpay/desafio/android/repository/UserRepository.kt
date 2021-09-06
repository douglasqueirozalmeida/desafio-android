package com.picpay.desafio.android.repository

import com.picpay.desafio.android.application.MyApplication
import com.picpay.desafio.android.base.BaseRepository
import com.picpay.desafio.android.database.dao.UserDAO
import com.picpay.desafio.android.model.Resource
import com.picpay.desafio.android.model.User


class UserRepository : BaseRepository() {

    suspend fun getUsers(): Resource<List<User>?> {
        val users: List<User>? = userDAO()!!.getAll()
        return if (users.isNullOrEmpty()) {
            val resposta = serviceApi.getUsers()

            if (resposta.isSuccessful) {
                resposta.body()?.let { userDAO()?.insertAll(it) }
                Resource.Success(resposta.body())
            } else {
                Resource.Error(resposta.message())
            }
        } else {
            Resource.Success(users)
        }
    }

    private fun userDAO(): UserDAO? = MyApplication.database?.userDAO
}