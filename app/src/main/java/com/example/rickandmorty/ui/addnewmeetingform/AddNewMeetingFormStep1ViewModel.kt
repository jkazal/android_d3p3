package com.example.rickandmorty.ui.addnewmeetingform

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*


class AddNewMeetingFormStep1ViewModel @ViewModelInject constructor(
) : ViewModel() {
    val topicName = MutableLiveData<String>("")
    val selectedDay = MutableLiveData<String>("")
    val selectedStartTime = MutableLiveData<String>("")
    val selectedEndTime = MutableLiveData<String>("")
}
