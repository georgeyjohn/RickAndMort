package com.caper.rickandmorty.webservice

import com.caper.rickandmorty.model.Characters
import retrofit2.Response
import retrofit2.http.GET

interface CharacterAPI {
  @GET("api/character/")
  suspend fun getCharacters(): Response<Characters>
}