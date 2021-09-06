package com.picpay.desafio.android.ui.fragment

import android.os.Bundle
import android.view.View
import com.picpay.desafio.android.R
import com.picpay.desafio.android.base.BaseFragment

class DetailUserFragment : BaseFragment(R.layout.fragment_detail_user) {

    private val detailUserFragmentArgs: DetailUserFragmentArgs by lazy {
        DetailUserFragmentArgs.fromBundle(requireArguments())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}