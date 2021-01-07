package com.example.rickandmorty.data.entities.spaces

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rickandmorty.data.entities.user.User

@Entity(tableName = "tool")
data class D3P3Tool(
    @PrimaryKey
    val id: String,
    val label: String
)