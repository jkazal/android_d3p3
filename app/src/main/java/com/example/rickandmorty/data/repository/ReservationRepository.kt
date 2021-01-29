package com.example.rickandmorty.data.repository

import android.util.Log

import com.example.rickandmorty.data.entities.Info
import com.example.rickandmorty.data.entities.reservation.Reservation
import com.example.rickandmorty.data.entities.reservation.ReservationAddResult
import com.example.rickandmorty.data.entities.reservation.ReservationList
import com.example.rickandmorty.data.entities.spaces.D3P3Space
import com.example.rickandmorty.data.entities.spaces.D3P3SpaceList
import com.example.rickandmorty.data.entities.spaces.D3P3SpaceType
import com.example.rickandmorty.data.entities.user.User
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
        // return remoteDataSource.getUpcomingMeetingsForDate(roomId, date)
        // [MOCK]
        var listInfo : Info = Info(0, "", 0, 0)
        var d3P3SpaceType : D3P3SpaceType = D3P3SpaceType("1", "Meeting room")

        var organizer = User(
            "1",
            "Johann",
            "KAZAL",
            "johannkazal@gmail.com"
        )
        var user1 = User(
            "2",
            "Thomas",
            "Dupont",
            "thomas.dupont@gmail.com"
        )
        var user2 = User(
            "3",
            "Henri",
            "Dupuit",
            "henri.dupuit@gmail.com"
        )
        var user3 = User(
            "4",
            "Jean",
            "Dubois",
            "jean.dubois@gmail.com"
        )
        var userList1 = ArrayList<User>()
        userList1.add(user1); userList1.add(user2)
        var userList2 = ArrayList<User>()
        userList2.add(user3)
        var space2 : D3P3Space = D3P3Space(
            "SALLE_202",
            "Salle 202",
            "Salle de cours",
            d3P3SpaceType,
            25
        )
        var reservation1 = Reservation(
            "2",
            "Shareholder meeting 1",
            "14:00", "14:30",
            "2021-01-10",
            organizer,
            space2,
            userList1,
            "Shareholder meeting 1"
        )
        var reservation2 = Reservation(
            "2",
            "Shareholder meeting 1",
            "14:00", "14:30",
            "2021-01-10",
            organizer,
            space2,
            userList2,
            "Shareholder meeting 1"
        )
        var rList = ArrayList<Reservation>()
        rList.add(reservation1)
        rList.add(reservation2)
        var rListTrue = ReservationList(listInfo, rList)
        return Resource.success<ReservationList>(rListTrue)

    }

    suspend fun getCurrentMeetingForRoom(roomId: String ) : Resource<Reservation> {
        // return remoteDataSource.getCurrentMeetingForRoom(roomId)
        // [MOCK]
        var d3P3SpaceType : D3P3SpaceType = D3P3SpaceType("1", "Meeting room")

        var organizer = User(
            "1",
            "Johann",
            "KAZAL",
            "johannkazal@gmail.com"
        )
        var user1 = User(
            "2",
            "Thomas",
            "Dupont",
            "thomas.dupont@gmail.com"
        )
        var user2 = User(
            "3",
            "Henri",
            "Dupuit",
            "henri.dupuit@gmail.com"
        )
        var user3 = User(
            "4",
            "Jean",
            "Dubois",
            "jean.dubois@gmail.com"
        )
        var userList1 = ArrayList<User>()
        userList1.add(user1); userList1.add(user2)
        var userList2 = ArrayList<User>()
        userList2.add(user3)
        var space2 : D3P3Space = D3P3Space(
            "SALLE_202",
            "Salle 202",
            "Salle de cours",
            d3P3SpaceType,
            25
        )
        var reservation1 = Reservation(
            "5",
            "Currentmeeting",
            "14:00", "14:30",
            "2021-01-10",
            organizer,
            space2,
            userList1,
            "Shareholder meeting 1"
        )
        return Resource.success<Reservation>(reservation1)
    }

    suspend fun createReservation(startTime: String,
                                  endTime: String,
                                  date: String,
                                  topicName: String,
                                  userIdArray: ArrayList<String>,
                                  roomId: String,
                                  userLogin: String,
                                  userPassword: String) : Resource<ReservationAddResult> {

        //return remoteDataSource.createReservation(
        //    startTime, endTime, date, topicName, userIdArray, roomId, userLogin, userPassword
        //)
        // [MOCK]
        Log.d("Johann", "frag3_valid")

        Log.d("userLogin", userLogin)
        Log.d("userPassword", userPassword)
        return Resource.success(ReservationAddResult(0, "Done"))
    }
}