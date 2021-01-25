package com.example.rickandmorty.data.remote.reservations

import com.example.rickandmorty.data.remote.BaseDataSource
import javax.inject.Inject

class ReservationRemoteDataSource @Inject constructor(
    private val reservationService: ReservationService
): BaseDataSource() {

//    suspend fun getCharacters() = getResult { reservationService.getAllCharacters() }
//    suspend fun getCharacter(id: Int) = getResult { reservationService.getCharacter(id) }
    // NOTE: La date doit être au format YYYY-MM-DD. (ex: 2021-01-09)
    suspend fun getUpcomingMeetingsForDate(
        roomId: String,
        date: String
    ) = getResult { reservationService.getReservations(roomId, date) }

    suspend fun getReservationById ( id: String ) = getResult { reservationService.getReservationById(id) }

    suspend fun getCurrentMeetingForRoom ( roomId: String ) = getResult { reservationService.getCurrentMeetingForRoom(roomId) }

    suspend fun createReservation (
        sTime: String,
        eTime: String,
        date: String,
        tName: String,
        userIdArray: ArrayList<String>,
        roomId: String,
        login: String,
        password: String
    ) = getResult { reservationService.createReservation(
        sTime,
        eTime,
        date,
        tName,
        userIdArray,
        roomId,
        login,
        password
    ) }
}