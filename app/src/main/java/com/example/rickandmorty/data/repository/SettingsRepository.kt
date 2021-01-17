package com.example.rickandmorty.data.repository

import androidx.lifecycle.LiveData
import com.example.rickandmorty.data.entities.Character
import com.example.rickandmorty.data.entities.CharacterList
import com.example.rickandmorty.data.entities.SettingsEntity
import com.example.rickandmorty.data.local.CharacterDao
import com.example.rickandmorty.data.local.SettingsDao
import com.example.rickandmorty.data.remote.CharacterRemoteDataSource
import com.example.rickandmorty.utils.Resource
import com.example.rickandmorty.utils.performGetOperation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class SettingsRepository @Inject constructor(
    private val localDataSource: SettingsDao
) {
    suspend fun getSettings() : LiveData<SettingsEntity> {
        return localDataSource.getCurrentSettings()
    }

    suspend fun updateSettings(roomId: String, date: String) {
        val entity: SettingsEntity = SettingsEntity(0, roomId, date)
        return localDataSource.insertSettings(entity)
    }
}