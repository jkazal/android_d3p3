package com.example.rickandmorty.data.remote.spaces

import com.example.rickandmorty.data.remote.BaseDataSource
import javax.inject.Inject

class SpaceRemoteDataSource @Inject constructor(
    private val spaceService: SpaceService
): BaseDataSource() {

    suspend fun getSpace(id: Int) = getResult { spaceService.getSpaceById(id) }
}