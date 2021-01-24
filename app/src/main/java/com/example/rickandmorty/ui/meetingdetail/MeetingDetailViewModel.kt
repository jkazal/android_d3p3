package com.example.rickandmorty.ui.meetingdetail

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.data.entities.CharacterList
import com.example.rickandmorty.data.entities.reservation.Reservation
import com.example.rickandmorty.data.repository.CharacterRepository
import com.example.rickandmorty.data.repository.ReservationRepository
import com.example.rickandmorty.utils.Resource
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MeetingDetailViewModel @ViewModelInject constructor(
    private val repository: ReservationRepository
) : ViewModel(), CoroutineScope {

    val meetingId = MutableLiveData<String>()
    val meeting : MutableLiveData<Resource<Reservation>> = MutableLiveData<Resource<Reservation>>()

    fun initCharacters() {
        launch {
            meeting.postValue(repository.getReservation())
            Log.d("DEBUG", characters.value.toString())
        }
    }

    // Récupérer les details de l'evenement, puis la liste des participants
    fun initMeetingDetails() {
        launch {

        }
    }

    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.IO
}
