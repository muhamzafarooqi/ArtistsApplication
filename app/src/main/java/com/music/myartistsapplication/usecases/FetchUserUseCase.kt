package com.music.myartistsapplication.usecases

import com.music.myartistsapplication.models.Node
import com.music.myartistsapplication.repositories.ArtistsRepository
import com.music.myartistsapplication.wrapIntoResult
import javax.inject.Inject

class FetchUserUseCase @Inject constructor(private val artistsRepository: ArtistsRepository) {

    suspend operator fun invoke(name: String = "as", isNewSearch: Boolean = false) = wrapIntoResult {
        artistsRepository.fetchArtists(name, isNewSearch)
    }

}