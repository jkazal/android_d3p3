package com.example.rickandmorty.data.entities.user

import com.example.rickandmorty.data.entities.Info

data class UserList (
    val info: Info,
    val results: List<User>
)