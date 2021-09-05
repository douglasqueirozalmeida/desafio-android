package com.picpay.desafio.android.service

import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.model.Resource
import com.picpay.desafio.android.repository.UserRepository

class UserService(private val userRepository: UserRepository) {

    suspend fun getUsers(): Resource<List<User>?> {
        return userRepository.getUsers()
    }
}