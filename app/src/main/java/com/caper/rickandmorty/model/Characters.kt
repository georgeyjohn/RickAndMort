package com.caper.rickandmorty.model

data class Characters(
    val results: List<Character>
)

data class  Character(
    val id: Int?,
    val name: String?,
    val species: String?,
    val status: String?,
    val image: String?,
    val location: Location?
)

data class Location(val name: String?)