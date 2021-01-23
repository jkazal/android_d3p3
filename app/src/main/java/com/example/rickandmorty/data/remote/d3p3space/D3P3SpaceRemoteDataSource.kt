package com.example.rickandmorty.data.remote.d3p3space

import com.example.rickandmorty.data.remote.BaseDataSource
import javax.inject.Inject

class D3P3SpaceRemoteDataSource @Inject constructor(
    private val d3p3spaceService: D3P3SpaceService
): BaseDataSource() {

    suspend fun getAllSpaces() = getResult { d3p3spaceService.getAllSpaces() }
}