package com.example.rickandmorty.ui.addnewmeetingform

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.data.entities.CharacterList
import com.example.rickandmorty.data.entities.user.UserList
import com.example.rickandmorty.data.repository.UserRepository
import com.example.rickandmorty.utils.Resource
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class AddNewMeetingFormStep2ViewModel @ViewModelInject constructor(
    private val repository: UserRepository
) : ViewModel(), CoroutineScope {

    var users : MutableLiveData<Resource<UserList>> = MutableLiveData<Resource<UserList>>()

    var topicName : String = ""
    var selectedDay : String = ""
    var selectedStartTime : String = ""
    var selectedEndTime : String = ""

    var selectedUsers = MutableLiveData<ArrayList<String>>()
    var selectedUsersArray = ArrayList<String>()

    // TODO: Remplir les vals topicName/selectedDay/etc. par les arguments de navigation (bundle)
    // ceci a été fait da,s le fragment

    fun initAddNewMeetingFormStep2() {
        launch {
            users.postValue(repository.getUsers())
            Log.d("DEBUG", users.value.toString())
        }
    }

    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.IO
}
