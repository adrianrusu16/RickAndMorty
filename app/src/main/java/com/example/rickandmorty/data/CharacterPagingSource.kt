package com.example.rickandmorty.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bumptech.glide.load.HttpException
import com.example.rickandmorty.api.CharacterApi
import java.io.IOException

private const val CHARACTER_STARTING_PAGE_INDEX = 1

class CharacterPagingSource(
    private val characterApi: CharacterApi
) : PagingSource<Int, Character>() {

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? =
        state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {
            val position = params.key ?: CHARACTER_STARTING_PAGE_INDEX

            val response = characterApi.searchCharacters(position)
            val characters = response.results

            val prevKey = if (position > 1) position - 1 else null
            val nextKey = if (characters.isNotEmpty()) position + 1 else null

            LoadResult.Page(
                data = characters,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}