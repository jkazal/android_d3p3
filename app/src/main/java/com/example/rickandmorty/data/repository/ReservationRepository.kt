package com.example.rickandmorty.data.repository

import com.example.rickandmorty.data.entities.Character
import com.example.rickandmorty.data.entities.CharacterList
import com.example.rickandmorty.data.entities.reservation.Reservation
import com.example.rickandmorty.data.entities.reservation.ReservationAddResult
import com.example.rickandmorty.data.entities.reservation.ReservationList
import com.example.rickandmorty.data.remote.reservations.ReservationRemoteDataSource
import com.example.rickandmorty.utils.Resource
import com.example.rickandmorty.utils.performGetOperation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class ReservationRepository @Inject constructor(
    private val remoteDataSource: ReservationRemoteDataSource
//    private val localDataSource: ReservationDao
) {
    /**
     * Renvoie une réservation spécifique
     */
    suspend fun getReservation(id: String) : Reservation? {
        return remoteDataSource.getReservationById(id).data
    }

    /**
     * Renvoie les meetings sur une journée en fonction de la date et du roomId fournis
     */
    suspend fun getUpcomingMeetingsForDate(roomId: String, date: String): Resource<ReservationList> {
        return remoteDataSource.getUpcomingMeetingsForDate(roomId, date)
    }

    suspend fun getCurrentMeetingForRoom(roomId: String ) : Resource<Reservation> {
        return remoteDataSource.getCurrentMeetingForRoom(roomId)
    }

    suspend fun createReservation(startTime: String, endTime: String, date: String, topicName: String, userIdArray: ArrayList<String>, roomId: String, userLogin: String, userPassword: String) : Resource<ReservationAddResult> {
        return remoteDataSource.createReservation(
            startTime, endTime, date, topicName, userIdArray, roomId, userLogin, userPassword
        )
    }
//    fun getCharacter(id: Int) = performGetOperation(
//        databaseQuery = { localDataSource.getCharacter(id) },
//        networkCall = { remoteDataSource.getCharacter(id) },
//        saveCallResult = { localDataSource.insert(it) }
//    )
//
//    fun getCharacters() = performGetOperation(
//        databaseQuery = { localDataSource.getAllCharacters() },
//        networkCall = { remoteDataSource.getCharacters() },
//        saveCallResult = { res -> localDataSource.insertAll(res.results) }
//    )
}