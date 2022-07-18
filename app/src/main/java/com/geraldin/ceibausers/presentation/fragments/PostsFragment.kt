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
import androidx.navigation.fragment.navArgs
import com.geraldin.ceibausers.databinding.PostFragmentBinding
import com.geraldin.ceibausers.presentation.states.PostState
import com.geraldin.ceibausers.presentation.viewmodels.PostsUserViewModel
import com.geraldin.ceibausers.util.adapters.PostAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PostsFragment : Fragment() {

    private var _binding: PostFragmentBinding? = null
    private val binding get() = _binding!!

    private val postViewModel: PostsUserViewModel by viewModels()
    private val args by navArgs<PostsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PostFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        postViewModel.loadUsers(args.id)
        chargeUsers()
    }

    private fun chargeUsers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                postViewModel.postState.collect { postState ->
                    when (postState) {
                        is PostState.Success -> {
                            binding.postsRecyclerView.adapter =
                                PostAdapter(
                                    postState.post
                                )
                        }
                        is PostState.Error -> {
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
}
