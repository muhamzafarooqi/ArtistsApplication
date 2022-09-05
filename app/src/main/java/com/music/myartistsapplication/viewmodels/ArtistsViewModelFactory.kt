package com.music.myartistsapplication.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.music.myartistsapplication.features.artists.artistslisting.ArtistsViewModel
import com.music.myartistsapplication.usecases.FetchUserUseCase
import com.music.myartistsapplication.usecases.SearchArtistsUseCase
import javax.inject.Inject

class ArtistsViewModelFactory @Inject constructor(
    private val artistsUseCase: FetchUserUseCase,
    val searchArtistsUseCase: SearchArtistsUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ArtistsViewModel(artistsUseCase, searchArtistsUseCase) as T
    }
}