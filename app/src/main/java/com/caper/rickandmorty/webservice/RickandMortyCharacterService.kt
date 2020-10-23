package com.caper.rickandmorty.webservice

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.reflect.KClass

object RickAndMortyCharService {
    const val BASE_URL  = "https://rickandmortyapi.com"

    inline fun <reified T : Any> getCharactersService(cls: KClass<T>): T {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(T::class.java)
    }


}