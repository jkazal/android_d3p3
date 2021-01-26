package com.example.rickandmorty.ui.addnewmeetingform

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.rickandmorty.data.entities.Character
import com.example.rickandmorty.data.entities.reservation.ReservationAddResult
import com.example.rickandmorty.data.repository.ReservationRepository
import com.example.rickandmorty.data.repository.SettingsRepository
import com.example.rickandmorty.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class AddNewMeetingFormStep3ViewModel @ViewModelInject constructor(
    private val repository: ReservationRepository,
    private val settingsRepository: SettingsRepository
) : ViewModel(), CoroutineScope {

    var topicName : String = ""
    var selectedDay : String = ""
    var selectedStartTime : String = ""
    var selectedEndTime : String = ""
    var selectedUserArray : ArrayList<String> = ArrayList<String>()
    var roomId: String = ""
    val userPasswordMLD = MutableLiveData<String>("")
    val userNameMLD = MutableLiveData<String>("")

    val results = MutableLiveData<Resource<ReservationAddResult>>()

    fun sendMeeting() {
        launch {
            results.postValue(repository.createReservation(
                selectedStartTime,
                selectedEndTime,
                selectedDay,
                topicName,
                selectedUserArray,
                roomId,
                userNameMLD.value.toString(),
                userPasswordMLD.value.toString()
            ))
        }
    }

//    private val _id = MutableLiveData<Int>()
//
////    private val _character = _id.switchMap { id ->
////        repository.getCharacter(id)
////    }
//    val character: LiveData<Resource<Character>> = _character
//
//
//    fun start(id: Int) {
//        _id.value = id
//    }


    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.IO

}
