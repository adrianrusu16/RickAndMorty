package com.example.rickandmorty.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.rickandmorty.data.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    repository: CharacterRepository
) : ViewModel() {

    val characters = repository.getSearchResults()
        .stateIn(
            scope = viewModelScope,
//            started = SharingStarted.WhileSubscribed(5000),
            started = SharingStarted.Lazily,
            initialValue = PagingData.empty()
        )
}