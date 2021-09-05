package com.picpay.desafio.android.base

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController

abstract class BaseFragment(@LayoutRes fragmentLayout: Int) : Fragment(fragmentLayout) {

    val navController by lazy {
        findNavController()
    }

    fun navigate(navDirection: NavDirections) {
        navController.navigate(navDirection)
    }
}