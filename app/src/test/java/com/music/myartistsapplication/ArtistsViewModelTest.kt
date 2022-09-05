package com.music.myartistsapplication

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.music.myartistsapplication.features.artists.artistslisting.ArtistsViewModel
import com.music.myartistsapplication.repositories.ArtistsRepository
import com.music.myartistsapplication.usecases.FetchUserUseCase
import com.music.myartistsapplication.usecases.SearchArtistsUseCase
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(AndroidJUnit4::class)
class ArtistsViewModelTest {

    @Mock
    lateinit var artistsRepository : ArtistsRepository

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var artistsViewModel: ArtistsViewModel

    @Mock
    lateinit var fetchArtistsUseCase : FetchUserUseCase

    @Mock
    lateinit var searchArtistsUseCase : SearchArtistsUseCase

    @Before
    fun setUp() {
        artistsRepository = Mockito.mock(ArtistsRepository::class.java)
        MockitoAnnotations.initMocks(this)
        artistsViewModel = ArtistsViewModel(fetchArtistsUseCase,searchArtistsUseCase)
    }

    @Test
    suspend fun check_null()
    {
        Mockito.`when`(fetchArtistsUseCase.invoke("as",false)).thenReturn(null)
        Assert.assertNotNull(artistsViewModel.artists)
    }

}