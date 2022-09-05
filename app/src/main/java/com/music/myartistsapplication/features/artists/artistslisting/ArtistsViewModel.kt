package com.music.myartistsapplication.features.artists.artistslisting

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.music.myartistsapplication.api.MyArtistsQuery
import com.music.myartistsapplication.base.BaseViewModel
import com.music.myartistsapplication.models.Node
import com.music.myartistsapplication.repositories.ArtistsRepository
import com.music.myartistsapplication.usecases.FetchUserUseCase
import com.music.myartistsapplication.usecases.SearchArtistsUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import com.music.myartistsapplication.Result

class ArtistsViewModel(val fetchArtistsUseCase: FetchUserUseCase, val searchArtistsUseCase: SearchArtistsUseCase) : BaseViewModel() {
    private var isLoading = false

    private val _artists = MutableLiveData<Result<List<MyArtistsQuery.Node?>?>?>()
    val artists: MutableLiveData<Result<List<MyArtistsQuery.Node?>?>?> = _artists

    init {
         viewModelScope.async {  _artists.value = fetchArtistsUseCase()
         }

    }

    fun loadMoreItems(name:String)
    {
        if(isLoading)
            return
        isLoading = true
      val res = viewModelScope.launch {
           _artists.value = fetchArtistsUseCase(name)
           isLoading = false
       }
    }

    fun fetchData(name: String, isNewSearch:Boolean)
    {
        viewModelScope.async {  _artists.value = fetchArtistsUseCase(name, isNewSearch) }
    }
}