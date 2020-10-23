package com.caper.rickandmorty.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.caper.rickandmorty.R
import com.caper.rickandmorty.model.Character
import com.caper.rickandmorty.utils.ImageLoader.loadImage


class CharacterListAdapter(var characters: ArrayList<Character>) :
    RecyclerView.Adapter<CharacterListAdapter.CharacterViewHolder>() {

    fun updateCharacter(newCharacters: List<Character>) {
        characters.clear()
        characters.addAll(newCharacters)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CharacterViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
    )

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(characters[position])
    }

    override fun getItemCount() = characters.size

    class CharacterViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val imvCharacter = view.findViewById<ImageView>(R.id.imvCharacter)
        private val tvCharacterName = view.findViewById<TextView>(R.id.tvName)
        private val tvStatus = view.findViewById<TextView>(R.id.tvStatus)
        private val tvSpecies = view.findViewById<TextView>(R.id.tvSpecies)
        private val clItemView = view.findViewById<ConstraintLayout>(R.id.item_root)


        fun bind(character: Character) {
            imvCharacter.loadImage(character.image)
            tvCharacterName.text = character.name
            tvStatus.text = character.status
            tvSpecies.text = character.species

            clItemView.setOnClickListener {
                
            }
        }
    }
}