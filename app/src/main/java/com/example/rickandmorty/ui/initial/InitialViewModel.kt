package com.example.rickandmorty.ui.initial

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.data.entities.CharacterList
import com.example.rickandmorty.data.entities.SettingsEntity
import com.example.rickandmorty.data.entities.reservation.Reservation
import com.example.rickandmorty.data.entities.reservation.ReservationList
import com.example.rickandmorty.data.local.SettingsDao
import com.example.rickandmorty.data.repository.CharacterRepository
import com.example.rickandmorty.data.repository.ReservationRepository
import com.example.rickandmorty.utils.Resource
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class InitialViewModel @ViewModelInject constructor(
    private val reservationRepository: ReservationRepository,
    private val settingsDao: SettingsDao
) : ViewModel(), CoroutineScope {

    val upcomingMeetings : MutableLiveData<Resource<ReservationList>> = MutableLiveData<Resource<ReservationList>>()

    fun initUpcomingMeetings() {
        getUpcomingMeetings()
    }

    fun refreshUpcomingMeetings() {
        getUpcomingMeetings()
    }

    fun getUpcomingMeetings() {
        launch {
            val settings: LiveData<SettingsEntity> = settingsDao.getCurrentSettings()
            upcomingMeetings.postValue(settings.value?.roomId?.let {
                settings.value?.date?.let { it1 ->
                    reservationRepository.getUpcomingMeetingsForDate(
                        it,
                        it1
                    )
                }
            })

            // si on arrive pas à lire de la base de manière sync :
            // val settingsEntity: SettingsEntity = settingsDao.getCurrentSettings() // (avec getCurrentSettings ne renvoyant pas un livedata)
            // upcomingMeetings.postValue(settingsEntity.roomId, settingsEntity.date)
        }
    }
    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.IO
}
