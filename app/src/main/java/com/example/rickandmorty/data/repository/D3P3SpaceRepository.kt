package com.example.rickandmorty.data.repository

import com.example.rickandmorty.data.entities.Character
import com.example.rickandmorty.data.entities.CharacterList
import com.example.rickandmorty.data.entities.spaces.D3P3SpaceList
import com.example.rickandmorty.data.remote.d3p3space.D3P3SpaceRemoteDataSource
import com.example.rickandmorty.utils.Resource
import com.example.rickandmorty.utils.performGetOperation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class D3P3SpaceRepository @Inject constructor(
    private val remoteDataSource: D3P3SpaceRemoteDataSource
) {
    suspend fun getAllSpaces(): Resource<D3P3SpaceList> {
        return remoteDataSource.getAllSpaces()
    }
}