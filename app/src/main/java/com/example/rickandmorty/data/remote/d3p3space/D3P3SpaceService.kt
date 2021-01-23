package com.example.rickandmorty.data.remote.d3p3space

import com.example.rickandmorty.data.entities.reservation.Reservation
import com.example.rickandmorty.data.entities.reservation.ReservationAddResult
import com.example.rickandmorty.data.entities.reservation.ReservationList
import com.example.rickandmorty.data.entities.spaces.D3P3SpaceList
import retrofit2.Response
import retrofit2.http.*


interface D3P3SpaceService {
    @GET("spaces/")
    suspend fun getAllSpaces() : Response<D3P3SpaceList>
}