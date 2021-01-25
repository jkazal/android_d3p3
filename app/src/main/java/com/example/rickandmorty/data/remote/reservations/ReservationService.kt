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
        @Field("startTime") startTime: String,
        @Field("endTime") endTime: String,
        @Field("date") date : String,
        @Field("topicName") topic : String,
        @Field("participants") participants: ArrayList<String>,
        @Field("roomId") roomId: String,
        @Field("organizerLogin") organizerLogin: String,
        @Field("organizerPassword") organizerPassword : String
    ): Response<ReservationAddResult>

    @GET("reservations/{reservationId}")
    suspend fun getReservationById ( @Field("reservationId") reservationId: String) : Response<Reservation>

    // Si il n'y a pas de meeting pour la salle donnée, on affiche rien
    @GET("reservations/{roomId}/current")
    suspend fun getCurrentMeetingForRoom ( @Field("roomId") roomId: String) : Response<Reservation>
}