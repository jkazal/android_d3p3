package com.example.rickandmorty.ui.addnewmeetingform

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.rickandmorty.data.entities.Character
import com.example.rickandmorty.data.repository.CharacterRepository
import com.example.rickandmorty.utils.Resource

class AddNewMeetingFormStep3ViewModel @ViewModelInject constructor(
    private val repository: CharacterRepository
) : ViewModel() {

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

}
