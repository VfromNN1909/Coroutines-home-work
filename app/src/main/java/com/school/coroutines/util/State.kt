package com.school.coroutines.util

import com.school.coroutines.model.Item

sealed class State {
    object Loading : State()
    data class Loaded(val content: List<Item>) : State()
}
