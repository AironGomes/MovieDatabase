package com.airongomes.moviedatabase.domain.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.airongomes.moviedatabase.domain.model.Movie
import com.airongomes.moviedatabase.domain.remote.NetworkResult
import com.airongomes.moviedatabase.domain.repository.Repository
import java.lang.Exception

class MoviePagingSource(
    private val repository: Repository
): PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val position = params.key ?: STARTING_PAGE
        val response = repository.getMoviesInTheaters(position)

        if (response is NetworkResult.Success) {
            val movieList = response.data

            movieList?.let {
                val nextKey = if (it.results.isEmpty() || it.page >= it.totalPages) {
                    null
                } else {
                    // initial load size = 3 * NETWORK_PAGE_SIZE
                    // ensure we're not requesting duplicating items, at the 2nd request
                    position + (params.loadSize / PAGE_SIZE)
                }

                return LoadResult.Page(
                    data = it.results,
                    prevKey = if (position == STARTING_PAGE) null else position - 1,
                    nextKey = nextKey
                )
            }
        }

        return LoadResult.Error(Exception("Unable to load data"))
    }

    companion object {
        const val STARTING_PAGE = 1
        const val PAGE_SIZE = 20
    }

}