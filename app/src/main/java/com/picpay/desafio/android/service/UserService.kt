package com.picpay.desafio.android.service

import com.picpay.desafio.android.model.Resource
import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.repository.UserRepository

class UserService(private val userRepository: UserRepository) {

    suspend fun getUsers(): Resource<List<User>?> {
        val resp = userRepository.getUsers()
        if (resp is Resource.Success) {
            resp.data?.let {
                return@getUsers Resource.Success(
                    it.sortedWith(compareBy<User> { obj -> obj.id }
                        .thenBy { obj -> obj.name }
                    ))
            }
        }

        return resp
    }
}