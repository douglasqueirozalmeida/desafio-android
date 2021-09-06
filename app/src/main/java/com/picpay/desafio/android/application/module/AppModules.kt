package com.picpay.desafio.android.application.module

import com.picpay.desafio.android.data.database.MyDataBase
import com.picpay.desafio.android.data.repository.UserRepository
import com.picpay.desafio.android.data.service.PicPayServiceApi
import com.picpay.desafio.android.data.service.ResquestApi
import com.picpay.desafio.android.data.cases.UserCase
import com.picpay.desafio.android.ui.viewmodel.UserViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val singleModule = module {
    single { ResquestApi.retrofit.create(PicPayServiceApi::class.java) }
    single { MyDataBase.getIntanceDatabase(androidApplication()) }
    single { get<MyDataBase>().userDAO }
}

val repositoryModule = module {
    factory { UserRepository(get(), get()) }
}

val serviceModule = module {
    factory { UserCase(get()) }
}

val viewModelModule = module {
    viewModel { UserViewModel(get()) }
}