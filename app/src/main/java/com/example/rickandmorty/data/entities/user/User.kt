package com.example.rickandmorty.data.entities.user

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

data class User(
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String

)