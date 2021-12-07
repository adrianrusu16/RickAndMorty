package com.example.rickandmorty.api

import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterApi {

    companion object {
        const val BASE_URL = "https://rickandmortyapi.com/api/"
    }

    @GET("character")
    suspend fun searchCharacters(
        @Query("page") page: Int,
    ): CharacterResponse
}