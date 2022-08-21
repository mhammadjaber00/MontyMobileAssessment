package com.mohammadjaber.montymobileassessmentapplication.domain.use_case.get_repos

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mohammadjaber.montymobileassessmentapplication.common.Constants.NETWORK_PAGE_SIZE
import com.mohammadjaber.montymobileassessmentapplication.domain.paging_source.ReposPagingSource
import com.mohammadjaber.montymobileassessmentapplication.domain.model.Repo
import com.mohammadjaber.montymobileassessmentapplication.domain.repository.RepoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetReposUseCase @Inject constructor(
    private val repository: RepoRepository
) {
    operator fun invoke(): Flow<PagingData<Repo>> = Pager(
        config = PagingConfig(pageSize = NETWORK_PAGE_SIZE),
    ) {
        ReposPagingSource(repository)
    }.flow
}