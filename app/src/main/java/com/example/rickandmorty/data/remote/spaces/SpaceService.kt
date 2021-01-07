package com.example.rickandmorty.data.remote.spaces

import com.example.rickandmorty.data.entities.reservation.ReservationAddResult
import com.example.rickandmorty.data.entities.reservation.ReservationList
import com.example.rickandmorty.data.entities.spaces.D3P3Space
import com.example.rickandmorty.data.entities.user.UserList
import retrofit2.Response
import retrofit2.http.*


interface SpaceService {
    @GET("space/{id}")
    suspend fun getSpaceById(@Path("id") spaceId: Int)
    : Response<D3P3Space>
}