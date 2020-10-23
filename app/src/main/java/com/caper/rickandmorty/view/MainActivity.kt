package com.caper.rickandmorty.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.caper.rickandmorty.R
import com.caper.rickandmorty.view.adapter.CharacterListAdapter
import com.caper.rickandmorty.viewmodel.CharacterListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var vmCharacterList: CharacterListViewModel
    private val charactersAdapter = CharacterListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vmCharacterList = ViewModelProvider(this).get(CharacterListViewModel::class.java)
        vmCharacterList.fetchCharacters()

        rvCharacters.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = charactersAdapter

            observeViewModel()
        }
    }

    private fun observeViewModel() {

        vmCharacterList.characters.observe(this, Observer { characters ->
            characters?.let {
                rvCharacters.visibility = View.VISIBLE
                charactersAdapter.updateCharacter(it)
            }
        })

        vmCharacterList.charactersLoadError.observe(this, Observer { isError ->
            tvErrorMessage.visibility = if (isError == null) View.GONE else View.VISIBLE
        })

        vmCharacterList.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                pbLoading.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    tvErrorMessage.visibility = View.GONE
                    rvCharacters.visibility = View.GONE
                }
            }
        })
    }


}