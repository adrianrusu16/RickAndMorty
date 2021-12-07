package com.example.rickandmorty.api

import com.example.rickandmorty.data.Character

data class CharacterResponse(
    val results: List<Character>
)