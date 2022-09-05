package com.music.myartistsapplication.di

import com.music.myartistsapplication.MainActivity
import com.music.myartistsapplication.features.artists.artistslisting.ArtistsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface ApplicationComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(artistsFragment: ArtistsFragment)
}