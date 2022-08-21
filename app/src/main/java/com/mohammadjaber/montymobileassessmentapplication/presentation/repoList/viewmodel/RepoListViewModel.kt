package com.mohammadjaber.montymobileassessmentapplication.presentation.repoList.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.mohammadjaber.montymobileassessmentapplication.domain.model.Repo
import com.mohammadjaber.montymobileassessmentapplication.domain.use_case.get_repos.GetReposUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class RepoListViewModel @Inject constructor(
    private val getReposUseCase: GetReposUseCase
) : ViewModel() {

    val queryLiveData = MutableLiveData<String>()

    fun getRepos(): Flow<PagingData<Repo>> {
        return getReposUseCase()
    }

}