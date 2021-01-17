package com.example.rickandmorty.ui.addnewmeetingform

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.data.entities.CharacterList
import com.example.rickandmorty.data.repository.CharacterRepository
import com.example.rickandmorty.utils.Resource
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class AddNewMeetingFormStep2ViewModel @ViewModelInject constructor(
    private val repository: CharacterRepository
) : ViewModel(), CoroutineScope {

    val characters : MutableLiveData<Resource<CharacterList>> = MutableLiveData<Resource<CharacterList>>()

    fun initAddNewMeetingFormStep2() {
        launch {
            characters.postValue(repository.getCharacters())
            Log.d("DEBUG", characters.value.toString())
        }
    }

    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.IO
}