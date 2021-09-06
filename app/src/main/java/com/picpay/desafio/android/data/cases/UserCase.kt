package com.picpay.desafio.android.data.cases

import com.picpay.desafio.android.data.model.Resource
import com.picpay.desafio.android.data.model.User
import com.picpay.desafio.android.data.repository.UserRepository

class UserCase(private val userRepository: UserRepository) {

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