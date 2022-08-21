package com.mohammadjaber.montymobileassessmentapplication.di

import com.mohammadjaber.montymobileassessmentapplication.common.Constants
import com.mohammadjaber.montymobileassessmentapplication.data.RepoApi
import com.mohammadjaber.montymobileassessmentapplication.data.repository.RepoRepositoryImpl
import com.mohammadjaber.montymobileassessmentapplication.domain.repository.RepoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesRepoApi(): RepoApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RepoApi::class.java)
    }

    @Provides
    @Singleton
    fun providesRepoRepository(api: RepoApi): RepoRepository {
        return RepoRepositoryImpl(api)
    }
}