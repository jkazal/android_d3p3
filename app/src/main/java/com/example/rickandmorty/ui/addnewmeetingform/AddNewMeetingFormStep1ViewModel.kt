package com.example.rickandmorty.ui.addnewmeetingform

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.rickandmorty.data.entities.Character
import com.example.rickandmorty.data.repository.CharacterRepository
import com.example.rickandmorty.utils.Resource

class AddNewMeetingFormStep1ViewModel @ViewModelInject constructor(
    private val repository: CharacterRepository
) : ViewModel() {

}
