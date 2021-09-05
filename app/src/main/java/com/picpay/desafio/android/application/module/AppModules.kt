package com.picpay.desafio.android.application.module

import com.picpay.desafio.android.repository.UserRepository
import com.picpay.desafio.android.service.UserService
import com.picpay.desafio.android.ui.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repositoryModule = module {
    factory { UserRepository() }
}

val serviceModule = module {
    factory { UserService(get()) }
}

val viewModelModule = module {
    viewModel { UserViewModel(get()) }
}