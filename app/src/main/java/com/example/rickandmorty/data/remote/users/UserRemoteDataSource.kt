package com.example.rickandmorty.data.remote.users

import com.example.rickandmorty.data.remote.BaseDataSource
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(
    private val userService: UserService
): BaseDataSource() {

//    suspend fun getCharacters() = getResult { reservationService.getAllCharacters() }
//    suspend fun getCharacter(id: Int) = getResult { reservationService.getCharacter(id) }
    suspend fun getUserSuggestions(userInput: String) = getResult { userService.getUserSuggestions(userInput) }

    suspend fun getAllUsers() = getResult {
        userService.getAllUsers()
    }
}