package com.example.rickandmorty.data.repository

import com.example.rickandmorty.data.entities.Character
import com.example.rickandmorty.data.entities.CharacterList
import com.example.rickandmorty.data.entities.user.UserList
import com.example.rickandmorty.data.local.CharacterDao
import com.example.rickandmorty.data.remote.CharacterRemoteDataSource
import com.example.rickandmorty.data.remote.users.UserRemoteDataSource
import com.example.rickandmorty.utils.Resource
import com.example.rickandmorty.utils.performGetOperation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class UserRepository @Inject constructor(
    private val remoteDataSource: UserRemoteDataSource
    // private val localDataSource: CharacterDao
) {
    suspend fun getUsers(): Resource<UserList> {
        return remoteDataSource.getAllUsers()
    }
    //suspend fun getCharacter(id: Int) : Character? {
    //    return remoteDataSource.getCharacter(id).data
    //}

    //suspend fun getCharacters(): Resource<CharacterList> {
    //    return remoteDataSource.getCharacters()
    //}


//    fun getCharacter(id: Int) = performGetOperation(
//        databaseQuery = { localDataSource.getCharacter(id) },
//        networkCall = { remoteDataSource.getCharacter(id) },
//        saveCallResult = { localDataSource.insert(it) }
//    )
//
//    fun getCharacters() = performGetOperation(
//        databaseQuery = { localDataSource.getAllCharacters() },
//        networkCall = { remoteDataSource.getCharacters() },
//        saveCallResult = { res -> localDataSource.insertAll(res.results) }
//    )
}