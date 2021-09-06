package com.picpay.desafio.android.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.android.R
import com.picpay.desafio.android.base.BaseFragment
import com.picpay.desafio.android.data.model.Resource
import com.picpay.desafio.android.data.model.User
import com.picpay.desafio.android.ui.adapter.user.UserListAdapter
import com.picpay.desafio.android.ui.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_lista_user.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListaUserFragment : BaseFragment(R.layout.fragment_lista_user) {

    private val userViewModel: UserViewModel by viewModel()
    private lateinit var adapter: UserListAdapter

    private val userClick = object : UserListAdapter.OnClickUser {
        override fun onClickUser(user: User) {
            navigate(ListaUserFragmentDirections.actionListaUserFragmentToDetailUserFragment(user))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initListUserObserver()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        adapter = UserListAdapter(userClick)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        userViewModel.getUsers()
    }


    private fun initListUserObserver() {
        userViewModel.listUser.observe(requireActivity()) {
            when (it) {
                is Resource.Success -> retornoSucessoUser(it)
                is Resource.Error -> retornoFalhaUser()
                is Resource.Loading -> loadingUser()
            }
        }
    }

    private fun loadingUser(loading: Boolean = true) {
        if (loading)
            user_list_progress_bar.visibility = View.VISIBLE
        else
            user_list_progress_bar.visibility = View.GONE
    }

    private fun retornoFalhaUser() {
        loadingUser(false)
        val message = getString(R.string.error)

        recyclerView.visibility = View.GONE

        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT)
            .show()
    }

    private fun retornoSucessoUser(resource: Resource.Success<List<User>?>) {
        loadingUser(false)
        resource.data?.let { data ->
            adapter.users = data
        }
    }
}