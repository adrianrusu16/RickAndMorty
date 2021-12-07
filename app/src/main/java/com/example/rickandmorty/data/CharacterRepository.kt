package com.example.rickandmorty.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.rickandmorty.api.CharacterApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterRepository @Inject constructor(private val characterApi: CharacterApi) {

    fun getSearchResults() =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { CharacterPagingSource(characterApi) }
        ).flow
}