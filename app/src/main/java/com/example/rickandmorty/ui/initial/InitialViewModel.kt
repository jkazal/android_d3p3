package com.example.rickandmorty.ui.initial

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.R
import com.example.rickandmorty.data.entities.reservation.Reservation
import com.example.rickandmorty.data.entities.reservation.ReservationList
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
    private val settingsRepository: SettingsRepository
) : ViewModel(), CoroutineScope {

    val upcomingMeetings : MutableLiveData<Resource<ReservationList>> = MutableLiveData<Resource<ReservationList>>()

    var date: String = ""
    var roomId: String = ""

    var dateMLD = MutableLiveData<String>("")
    var roomNumberMLD = MutableLiveData<String>("")
    var openLabelMLD = MutableLiveData<String>("")
    val closedLabelMLD = MutableLiveData<String>("")

    val currentMeetingMLD : MutableLiveData<Resource<Reservation>> = MutableLiveData<Resource<Reservation>>()
    fun initUpcomingMeetings() {

        getUpcomingMeetings()
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
            dateMLD.postValue(date)
            roomNumberMLD.postValue(roomId)
            Log.d("Johann", "getUpcomingMeetingData")
            upcomingMeetings.postValue(reservationRepository.getUpcomingMeetingsForDate(roomId, date))
        }
    }

    fun getCurrentMeetingData() {
        launch {
            Log.d("Johann", "getCurrentData")
            currentMeetingMLD.postValue(reservationRepository.getCurrentMeetingForRoom(roomId) )
        }
    }
    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.IO
}
