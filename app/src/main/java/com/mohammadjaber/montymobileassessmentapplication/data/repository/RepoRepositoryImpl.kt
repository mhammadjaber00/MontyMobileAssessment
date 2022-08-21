package com.mohammadjaber.montymobileassessmentapplication.data.repository

import com.mohammadjaber.montymobileassessmentapplication.data.RepoApi
import com.mohammadjaber.montymobileassessmentapplication.data.remote.dto.RepoDto
import com.mohammadjaber.montymobileassessmentapplication.domain.repository.RepoRepository
import javax.inject.Inject

class RepoRepositoryImpl @Inject constructor(
    private val api: RepoApi,
) : RepoRepository {

    override suspend fun getRepos(page: Int?): List<RepoDto> {
        return api.getRepos(page)
    }
}