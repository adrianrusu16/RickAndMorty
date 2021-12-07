package com.example.rickandmorty.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmorty.R
import com.example.rickandmorty.data.Character
import com.example.rickandmorty.databinding.CharacterDetailsBinding

class CharacterAdapter :
    PagingDataAdapter<Character, CharacterAdapter.CharacterViewHolder>(CHARACTER_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding =
            CharacterDetailsBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val currentItem = getItem(position)

        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    class CharacterViewHolder(
        private val binding: CharacterDetailsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(character: Character) {
            binding.apply {
                characterName.text = character.name
                characterInformation.text = root.context.getString(
                    R.string.character_information_format,
                    character.status,
                    character.species
                )
                Glide
                    .with(itemView)
                    .load(character.image)
                    .centerCrop()
                    .into(characterImage)
            }
        }
    }

    companion object {
        private val CHARACTER_COMPARATOR = object : DiffUtil.ItemCallback<Character>() {
            override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean =
                oldItem == newItem
        }
    }
}