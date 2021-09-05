package com.picpay.desafio.android.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.android.R
import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.ui.adapter.user.UserListAdapter
import com.picpay.desafio.android.base.BaseFragment
import com.picpay.desafio.android.model.Resource
import com.picpay.desafio.android.ui.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_lista_user.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListaUserFragment : BaseFragment(R.layout.fragment_lista_user) {

    private val userViewModel: UserViewModel by viewModel()
    private lateinit var adapter: UserListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initListUserObserver()
    }

    private fun initListUserObserver() {
        userViewModel.listUser.observe(requireActivity()) {
            when (it) {
                is Resource.Success -> {
                    user_list_progress_bar.visibility = View.GONE

                    it.data?.let { data ->
                        adapter.users = data
                    }
                }
                is Resource.Error -> {
                    val message = getString(R.string.error)

                    user_list_progress_bar.visibility = View.GONE
                    recyclerView.visibility = View.GONE

                    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT)
                        .show()
                }
                else -> {
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = UserListAdapter(object : UserListAdapter.OnClickUser {
            override fun onClickUser(user: User) {
                navigate(ListaUserFragmentDirections.actionListaUserFragmentToDetailUserFragment())
            }
        })
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        user_list_progress_bar.visibility = View.VISIBLE

        userViewModel.getUsers()
    }
}