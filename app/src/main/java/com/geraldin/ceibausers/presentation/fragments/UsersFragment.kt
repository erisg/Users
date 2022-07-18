package com.geraldin.ceibausers.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.geraldin.ceibausers.databinding.UserFragmentBinding
import com.geraldin.ceibausers.presentation.UsersState
import com.geraldin.ceibausers.presentation.viewmodels.UserViewModel
import com.geraldin.ceibausers.util.IUserSelect
import com.geraldin.ceibausers.util.UserAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UsersFragment : Fragment(), IUserSelect {

    private var _binding: UserFragmentBinding? = null
    private val binding get() = _binding!!

    private val userViewModel: UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = UserFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userViewModel.loadUsers()
        chargeUsers()
    }

    private fun chargeUsers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                userViewModel.usersState.collect { usersState ->
                    when (usersState) {
                        is UsersState.Success -> {
                            binding.usersRecyclerView.adapter =
                                UserAdapter(
                                    usersState.users,
                                    this@UsersFragment
                                )
                        }
                        is UsersState.Error -> {
                            Toast.makeText(
                                requireContext(),
                                "Ups a ocurrido un error",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        else -> Unit
                    }
                }
            }
        }
    }

    fun filteredUser() {
        binding.searchView
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun selectedUser(id: Int) {
        TODO("Not yet implemented")
    }
}
