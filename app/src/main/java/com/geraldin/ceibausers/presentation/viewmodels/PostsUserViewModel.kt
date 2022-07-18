package com.geraldin.ceibausers.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geraldin.ceibausers.domain.uc.PostUC
import com.geraldin.ceibausers.presentation.states.PostState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

@HiltViewModel
class PostsUserViewModel @Inject constructor(private val postUC: PostUC) : ViewModel() {

    private val _postState: MutableStateFlow<PostState> = MutableStateFlow(
        PostState.Success(emptyList())
    )
    val postState: StateFlow<PostState> = _postState

    fun loadUserPost(id: Int) {
        viewModelScope.launch {
            postUC.getAllPost(id)
                .catch {
                    _postState.value = PostState.Error(Throwable(this.toString()))
                }
                .collect { result ->
                    _postState.value = PostState.Success(result)
                }
        }
    }
}
