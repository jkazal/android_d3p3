package com.example.rickandmorty.data.entities.reservation

import com.example.rickandmorty.data.entities.Info

data class ReservationList (
    val info: Info,
    val results: List<Reservation>
)