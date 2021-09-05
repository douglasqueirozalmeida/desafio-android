package com.picpay.desafio.android.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.model.Resource
import com.picpay.desafio.android.service.UserService
import kotlinx.coroutines.launch

class UserViewModel(private val userService: UserService) : ViewModel() {

    private val _listUser = MutableLiveData<Resource<List<User>?>>()
    val listUser: LiveData<Resource<List<User>?>> = _listUser

    fun getUsers() {
        viewModelScope.launch {
            _listUser.postValue(userService.getUsers())
        }
    }
}