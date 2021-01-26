package com.example.rickandmorty.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

data class Character(
    val created: String,
    val gender: String,
    val id: Int,
    val image: String,
    val name: String,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)