package com.mohammadjaber.montymobileassessmentapplication.domain.repository

import androidx.paging.PagingSource
import com.mohammadjaber.montymobileassessmentapplication.data.remote.dto.RepoDto
import com.mohammadjaber.montymobileassessmentapplication.domain.model.Repo

interface RepoRepository {
    suspend fun getRepos(page: Int?): List<RepoDto>
}