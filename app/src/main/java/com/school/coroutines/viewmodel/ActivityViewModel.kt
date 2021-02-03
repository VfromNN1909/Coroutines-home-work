package com.school.coroutines.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.school.coroutines.api.Repository
import com.school.coroutines.util.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ActivityViewModel : ViewModel() {
    private val _state = MutableLiveData<State>(State.Loading)
    val state: LiveData<State>
        get() = _state

    init {
        loadPosts()
    }

    fun loadPosts() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val postsResponse = Repository.getPosts()
                if (postsResponse.isSuccessful) {
                    postsResponse.body()?.let {
                        _state.postValue(State.Loaded(it))
                    }
                }
            } catch (e: Exception) {
                _state.postValue(State.Loaded(listOf()))
            }
        }
    }
}
