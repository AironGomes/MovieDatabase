package com.airongomes.moviedatabase.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.airongomes.moviedatabase.domain.model.Movie
import com.airongomes.moviedatabase.domain.remote.response.toModel
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
        try {
            val nextPage = params.key ?: STARTING_PAGE
            val response = repository.getMoviesInTheaters(nextPage)
            if (response.isSuccessful) { //TODO: Handle the response in the safe api?
                val body = response.body()
                body?.let {
                    val movieList = it.toModel()
                    return LoadResult.Page(
                        data = movieList.results,
                        prevKey = if(nextPage == STARTING_PAGE) null else nextPage - 1,
                        nextKey = if(movieList.page >= movieList.totalPages) null else movieList.page + 1
                    )
                }
            }
            return LoadResult.Error(Exception("Error: Unable to load data"))
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    companion object {
        const val STARTING_PAGE = 1
    }

}