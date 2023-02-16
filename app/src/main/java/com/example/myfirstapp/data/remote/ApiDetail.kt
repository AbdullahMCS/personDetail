package com.example.myfirstapp.data.remote

import com.example.myfirstapp.data.model.people.PeopleModel
import com.example.myfirstapp.data.model.room.RoomModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiDetail {

    @GET(ApiReference.PEOPLE)
    suspend fun getPeople(): Response<PeopleModel>

    @GET(ApiReference.ROOM)
    suspend fun getRoom(): Response<RoomModel>

}