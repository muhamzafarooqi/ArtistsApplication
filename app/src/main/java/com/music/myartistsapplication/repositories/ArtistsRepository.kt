package com.music.myartistsapplication.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.music.myartistsapplication.api.MyArtistsQuery
import com.music.myartistsapplication.models.Node
import com.music.myartistsapplication.retrofit.GraphqlAPI
import javax.inject.Inject
import kotlin.properties.Delegates

class ArtistsRepository @Inject constructor(private val apolloClient: ApolloClient) {

    private val _artists = MutableLiveData<List<MyArtistsQuery.Node?>?>()
    val artists: LiveData<List<MyArtistsQuery.Node?>?> = _artists
    var endCursor: String? = ""

    suspend fun fetchArtists(
        name: String,
        isNewSearch: Boolean
    ): List<MyArtistsQuery.Node?>? {

        if (isNewSearch) {
            _artists.value = arrayListOf()
            endCursor = ""
        }
        if (endCursor == null)
            return _artists.value
        Log.e("Tag", "End cursonr : $endCursor")
        val response = apolloClient.query(MyArtistsQuery(name, endCursor!!)).execute()
        val fetchedArtists = response.data?.search?.artists
        endCursor = fetchedArtists?.pageInfo?.endCursor

        if (_artists.value == null) {
            _artists.value = fetchedArtists?.nodes!!
        } else (_artists.value as MutableList).addAll(fetchedArtists?.nodes!!)
        Log.e("Tag", "Artists : " + _artists.value.toString())

        return _artists.value
    }

    suspend fun searchArtists(name: String): List<MyArtistsQuery.Node?>? {
        endCursor = ""
        _artists.value = arrayListOf()
        if (name.isNullOrEmpty())
            return _artists.value
        val response = apolloClient.query(MyArtistsQuery(name, endCursor!!)).execute()
        val fetchedArtists = response.data?.search?.artists
        endCursor = fetchedArtists?.pageInfo?.endCursor
        return _artists.value
    }

}