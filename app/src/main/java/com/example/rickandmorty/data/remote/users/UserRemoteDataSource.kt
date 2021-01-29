package com.example.rickandmorty.data.remote.users

import com.example.rickandmorty.data.remote.BaseDataSource
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(
    private val userService: UserService
): BaseDataSource() {

    suspend fun getUserSuggestions(userInput: String) = getResult { userService.getUserSuggestions(userInput) }

    suspend fun getAllUsers() = getResult {
        userService.getAllUsers()
    }
}