package com.example.rickandmorty.data.entities.spaces

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rickandmorty.data.entities.user.User

data class D3P3Space(
    val id: String,
    val name: String,
    val description: String,
    val type: D3P3SpaceType,
    val capacity: Int
)