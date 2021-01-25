package com.example.rickandmorty.data.entities.reservation

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rickandmorty.data.entities.spaces.D3P3Space
import com.example.rickandmorty.data.entities.user.User
import java.util.*

@Entity(tableName = "reservations")
data class Reservation(
    @PrimaryKey
    val id: String,
    val label: String,
    val dateDebut: String,
    val dateFin: String,
    val date: String,
    val organizer: User,
    val space: D3P3Space,
    val participants: List<User>,
    val topicName: String
)