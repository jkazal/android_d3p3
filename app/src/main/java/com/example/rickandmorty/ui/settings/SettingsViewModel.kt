package com.example.rickandmorty.ui.settings

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.data.entities.CharacterList
import com.example.rickandmorty.data.entities.spaces.D3P3Space
import com.example.rickandmorty.data.entities.spaces.D3P3SpaceList
import com.example.rickandmorty.data.repository.D3P3SpaceRepository
import com.example.rickandmorty.utils.Resource
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class SettingsViewModel @ViewModelInject constructor(
    private val repository: D3P3SpaceRepository
) : ViewModel(), CoroutineScope {
    val spaces: MutableLiveData<Resource<D3P3SpaceList>> = MutableLiveData<Resource<D3P3SpaceList>>()
    fun initSettings() {
        launch {
            spaces.postValue(repository.getAllSpaces())
        }
    }
    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.IO
}
