package com.example.rickandmorty.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

data class SettingsEntity(
    val id: Int,
    val roomId: String,
    val date: String
)