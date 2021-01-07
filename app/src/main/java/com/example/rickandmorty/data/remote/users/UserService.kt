package com.example.rickandmorty.data.remote.users

import com.example.rickandmorty.data.entities.reservation.ReservationAddResult
import com.example.rickandmorty.data.entities.reservation.ReservationList
import com.example.rickandmorty.data.entities.user.UserList
import retrofit2.Response
import retrofit2.http.*


interface UserService {
    @GET("users/search?searchValue={input}")
    suspend fun getUserSuggestions(@Path("input") inputText: String)
    : Response<UserList>
}