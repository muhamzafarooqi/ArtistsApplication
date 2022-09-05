package com.music.myartistsapplication.retrofit

import com.music.myartistsapplication.models.Node
import retrofit2.Response
import retrofit2.http.GET

interface GraphqlAPI {

    @GET("artists")
    suspend fun getArtists() : Response<List<Node>>
}