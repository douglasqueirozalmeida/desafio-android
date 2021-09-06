package com.picpay.desafio.android.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.data.model.Resource
import com.picpay.desafio.android.data.model.User
import com.picpay.desafio.android.data.cases.UserCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(private val userCase: UserCase) : ViewModel() {

    private val _listUser = MutableLiveData<Resource<List<User>?>>()
    val listUser: LiveData<Resource<List<User>?>> = _listUser

    fun getUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            _listUser.postValue(Resource.Loading())
            _listUser.postValue(userCase.getUsers())
        }
    }
}