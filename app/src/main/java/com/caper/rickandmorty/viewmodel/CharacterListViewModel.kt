package com.caper.rickandmorty.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.caper.rickandmorty.model.Character
import com.caper.rickandmorty.webservice.CharacterAPI
import com.caper.rickandmorty.webservice.RickAndMortyCharService
import kotlinx.coroutines.*

class CharacterListViewModel : ViewModel() {
    val characters = MutableLiveData<List<Character>>()
    val charactersLoadError = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()
    val characterService = RickAndMortyCharService.getCharactersService(CharacterAPI::class)
    var job: Job? = null

    fun fetchCharacters() {
        loading.value = true

        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = characterService.getCharacters()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    characters.value = response.body()?.results
                    charactersLoadError.value = null
                    loading.value = false
                } else {
                    onErrorMessage("Error : ${response.message()}")
                }
            }

        }
    }

    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        CoroutineScope(Dispatchers.Main).launch {
            onErrorMessage("Error : ${throwable.localizedMessage}")
        }
    }

    private fun onErrorMessage(message: String) {
        loading.value = false
        charactersLoadError.value = message
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}