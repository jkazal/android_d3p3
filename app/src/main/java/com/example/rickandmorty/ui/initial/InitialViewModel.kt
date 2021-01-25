package com.example.rickandmorty.ui.initial

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.R
import com.example.rickandmorty.data.entities.SettingsEntity
import com.example.rickandmorty.data.entities.reservation.Reservation
import com.example.rickandmorty.data.entities.reservation.ReservationList
import com.example.rickandmorty.data.local.SettingsDao
import com.example.rickandmorty.data.repository.ReservationRepository
import com.example.rickandmorty.data.repository.SettingsRepository
import com.example.rickandmorty.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import kotlin.coroutines.CoroutineContext

class InitialViewModel @ViewModelInject constructor(
    private val reservationRepository: ReservationRepository,
    private val settingsDao: SettingsDao,
    private val settingsRepository: SettingsRepository
) : ViewModel(), CoroutineScope {

    val upcomingMeetings : MutableLiveData<Resource<ReservationList>> = MutableLiveData<Resource<ReservationList>>()
    var currentTime: String =
        SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
    var date: String = ""
    var roomId: String = ""

    var roomNumberMLD = MutableLiveData<String>("")
    var openLabelMLD = MutableLiveData<String>("")
    val closedLabelMLD = MutableLiveData<String>("")

    val currentMeetingMLD : MutableLiveData<Resource<Reservation>> = MutableLiveData<Resource<Reservation>>()
    fun initUpcomingMeetings() {
        getUpcomingMeetings()
    }

    fun turnTimeToNumber(time: String): Int {
        val strNew = time.replace(":", "")
        return Integer.parseInt(strNew)
    }

    fun getSettingsRepo () : SettingsRepository{
        return settingsRepository
    }
    fun String.insert(index: Int, string: String): String {
        return this.substring(0, index) + string + this.substring(index, this.length)
    }

    fun turnNumberToTime(time: Int): String {
        val strNew : String = time.toString()
        return strNew.insert(2, ":")
    }

    fun refreshUpcomingMeetings() {
        getUpcomingMeetings()
    }

    fun getUpcomingMeetings() {
        launch {
//            val settings: LiveData<SettingsEntity> = settingsDao.getCurrentSettings()
//            upcomingMeetings.postValue(settings.value?.roomId?.let {
//                settings.value?.date?.let { it1 ->
//                    reservationRepository.getUpcomingMeetingsForDate(
//                        it,
//                        it1
//                    )
//                }
//            })
            roomNumberMLD.postValue(roomId)
            openLabelMLD.postValue("Open")
            closedLabelMLD.postValue("Closed")
            upcomingMeetings.postValue(reservationRepository.getUpcomingMeetingsForDate(roomId, date))

            // si on arrive pas à lire de la base de manière sync :
            // val settingsEntity: SettingsEntity = settingsDao.getCurrentSettings() // (avec getCurrentSettings ne renvoyant pas un livedata)
            // upcomingMeetings.postValue(settingsEntity.roomId, settingsEntity.date)
        }
    }

    fun getCurrentMeetingData() {
        launch {
            currentMeetingMLD.postValue(reservationRepository.getCurrentMeetingForRoom(roomId) )
            val reservationId = currentMeetingMLD.value?.data?.id

        }
    }
    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.IO
}
