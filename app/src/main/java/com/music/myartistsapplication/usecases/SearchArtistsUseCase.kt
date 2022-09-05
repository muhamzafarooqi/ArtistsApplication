package com.music.myartistsapplication.usecases

import com.music.myartistsapplication.api.MyArtistsQuery
import com.music.myartistsapplication.repositories.ArtistsRepository
import javax.inject.Inject

class SearchArtistsUseCase @Inject constructor(private val artistsRepository: ArtistsRepository){
    suspend operator fun invoke(name:String):List<MyArtistsQuery.Node?>?
    {
       return artistsRepository.searchArtists(name)
    }

}