package com.mohammadjaber.montymobileassessmentapplication.data

import com.mohammadjaber.montymobileassessmentapplication.data.remote.dto.RepoDto
import retrofit2.http.GET
import retrofit2.http.Query

interface RepoApi {
    @GET("/orgs/google/repos")
    suspend fun getRepos(
        @Query("page") page: Int?
    ): List<RepoDto>
}