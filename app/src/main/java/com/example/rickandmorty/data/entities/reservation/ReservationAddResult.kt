package com.example.rickandmorty.data.entities.reservation

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rickandmorty.data.entities.spaces.D3P3Space
import com.example.rickandmorty.data.entities.user.User


data class ReservationAddResult(
    val code: Int,
    val description: String
)