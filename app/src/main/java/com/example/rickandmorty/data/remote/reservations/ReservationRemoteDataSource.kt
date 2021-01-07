package com.example.rickandmorty.data.remote.reservations

import com.example.rickandmorty.data.remote.BaseDataSource
import javax.inject.Inject

class ReservationRemoteDataSource @Inject constructor(
    private val reservationService: ReservationService
): BaseDataSource() {

//    suspend fun getCharacters() = getResult { reservationService.getAllCharacters() }
//    suspend fun getCharacter(id: Int) = getResult { reservationService.getCharacter(id) }
    suspend fun getReservations(
        roomId: Int,
        date: String
    ) = getResult { reservationService.getReservations(roomId, date) }

    suspend fun createReservation (
        dDebut: String,
        hDebut: String,
        dFin: String,
        hFin: String,
        orgLogin: String,
        orgPassword: String,
        spaceId: String,
        participantIdList: Array<String>
    ) = getResult { reservationService.createReservation(
        dDebut,
        hDebut,
        dFin,
        hFin,
        orgLogin,
        orgPassword,
        spaceId,
        participantIdList
    ) }
}