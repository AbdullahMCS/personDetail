package com.example.myfirstapp.data.repository

import com.example.myfirstapp.data.model.people.PeopleModel
import com.example.myfirstapp.data.model.room.RoomModel
import com.example.myfirstapp.data.remote.ApiDetail
import retrofit2.Response
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    val apiDetail: ApiDetail
): Repository {

    override suspend fun getPeople(): Response<PeopleModel> {
        return apiDetail.getPeople()
    }

    override suspend fun getRoom(): Response<RoomModel> {
        return apiDetail.getRoom()
    }
}