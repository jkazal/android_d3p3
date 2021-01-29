package com.example.rickandmorty.data.repository

import com.example.rickandmorty.data.entities.Info
import com.example.rickandmorty.data.entities.spaces.D3P3Space
import com.example.rickandmorty.data.entities.spaces.D3P3SpaceList
import com.example.rickandmorty.data.entities.spaces.D3P3SpaceType
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
        // return remoteDataSource.getAllSpaces()
        // [MOCK]
        var listInfo : Info = Info(0, "", 0, 0)
        var d3P3SpaceType : D3P3SpaceType = D3P3SpaceType("1", "Meeting room")
        var space1 : D3P3Space = D3P3Space(
            "SALLE_201",
            "Salle 201",
            "Salle de cours",
            d3P3SpaceType,
            25
        )
        var space2 : D3P3Space = D3P3Space(
            "SALLE_202",
            "Salle 202",
            "Salle de cours",
            d3P3SpaceType,
            25
        )
        var spListBasic = ArrayList<D3P3Space>()
        spListBasic.add(space1)
        spListBasic.add(space2)
        var spList = D3P3SpaceList(listInfo, spListBasic)
        var returnResourceSpaceList : Resource<D3P3SpaceList> = Resource.success<D3P3SpaceList>(spList)
        return returnResourceSpaceList
    }
}