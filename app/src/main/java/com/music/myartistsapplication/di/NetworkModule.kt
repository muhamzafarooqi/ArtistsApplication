package com.music.myartistsapplication.di

import com.apollographql.apollo3.ApolloCall
import com.apollographql.apollo3.ApolloClient
import com.music.myartistsapplication.retrofit.GraphqlAPI
import com.music.myartistsapplication.utils.Constants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL).build()
    }

    @Singleton
    @Provides
    fun providesGraphqlAPI(retrofit: Retrofit) : GraphqlAPI {
        return retrofit.create(GraphqlAPI::class.java)
    }

    @Singleton
    @Provides
    fun providesApollo():ApolloClient {
        return ApolloClient.Builder()
            .serverUrl("https://graphbrainz.herokuapp.com/graphql")
            .build()
    }

}