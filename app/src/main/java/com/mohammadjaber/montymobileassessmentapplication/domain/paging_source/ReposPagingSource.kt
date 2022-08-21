package com.mohammadjaber.montymobileassessmentapplication.domain.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mohammadjaber.montymobileassessmentapplication.data.remote.dto.toRepo
import com.mohammadjaber.montymobileassessmentapplication.domain.model.Repo
import com.mohammadjaber.montymobileassessmentapplication.domain.repository.RepoRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ReposPagingSource @Inject constructor(
    private val repository: RepoRepository
) : PagingSource<Int, Repo>() {

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, Repo> {
        return try {
            val pageIndex = params.key ?: 1
            val response = repository.getRepos(pageIndex)
            val next = pageIndex + 1
            LoadResult.Page(
                data = response.map { it.toRepo() },
                prevKey = null,
                nextKey = next
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Repo>): Int? {
        return state.anchorPosition?.let { 1 }
    }
}