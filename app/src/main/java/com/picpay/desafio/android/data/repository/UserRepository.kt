package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.database.dao.UserDAO
import com.picpay.desafio.android.data.model.Resource
import com.picpay.desafio.android.data.model.User
import com.picpay.desafio.android.data.service.PicPayServiceApi


class UserRepository(private val serviceApi: PicPayServiceApi, private val userDAO: UserDAO) {

    suspend fun getUsers(): Resource<List<User>?> {
        val users: List<User>? = userDAO.getAll()
        return if (users.isNullOrEmpty()) {
            val resposta = serviceApi.getUsers()

            if (resposta.isSuccessful) {
                resposta.body()?.let { userDAO.insertAll(it) }
                Resource.Success(resposta.body())
            } else {
                Resource.Error(resposta.message())
            }
        } else {
            Resource.Success(users)
        }
    }
}