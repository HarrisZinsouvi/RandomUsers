package com.example.randomusers.ui.lib

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.randomusers.models.User
import com.example.randomusers.repositories.UserRepository
import com.example.randomusers.utils.Constants.Companion.DEFAULT_SEED

class UserPagingSource(private val userRepository: UserRepository) : PagingSource<Int, User>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        return try {
            val nextPageNumber = params.key ?: 1
            val users = userRepository.getUsers(nextPageNumber, params.loadSize, DEFAULT_SEED)
            LoadResult.Page(
                data = users.results,
                prevKey = if (nextPageNumber == 1) null else nextPageNumber - 1,
                nextKey = if (users.results.isEmpty()) null else nextPageNumber + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        TODO("Not yet implemented")
    }
}