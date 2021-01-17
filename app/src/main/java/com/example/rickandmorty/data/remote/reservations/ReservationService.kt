package com.example.rickandmorty.data.remote.reservations

import com.example.rickandmorty.data.entities.reservation.Reservation
import com.example.rickandmorty.data.entities.reservation.ReservationAddResult
import com.example.rickandmorty.data.entities.reservation.ReservationList
import retrofit2.Response
import retrofit2.http.*


interface ReservationService {
//    @GET("character")
//    suspend fun getAllCharacters() : Response<CharacterList>
//
//    @GET("character/{id}")
//    suspend fun getCharacter(@Path("id") id: Int): Response<Character>

    @GET("reservations/upcoming?roomId={roomId}&date={YyyyMmDdDate}")
    suspend fun getReservations(@Path("roomId") roomId: String,
                                @Path("YyyyMmDdDate") date: String)
                                : Response<ReservationList>

    @POST("/reservations/add")
    @FormUrlEncoded
    suspend fun createReservation (
        @Field("dateDebut") dateDebut: String,
        @Field("heureDebut") heureDebut: String,
        @Field("dateFin") dateFin: String,
        @Field("heureFin") heureFin: String,
        @Field("organizerUser") organizerUser: String,
        @Field("organizerPassword") organizerPassword: String,
        @Field("spaceId") spaceId: String,
        @Field("participantIdList") participantIdList: Array<String>
    ): Response<ReservationAddResult>

    @GET("reservations/{reservationId}")
    suspend fun getReservationById ( @Field("reservationId") reservationId: String) : Response<Reservation>

}