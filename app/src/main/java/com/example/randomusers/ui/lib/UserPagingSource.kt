package com.example.randomusers.ui.lib

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.randomusers.models.User
import com.example.randomusers.repositories.UserRepository
import com.example.randomusers.utils.Constants.Companion.DEFAULT_SEED
import java.io.IOException
import retrofit2.HttpException

class UserPagingSource(private val userRepository: UserRepository) : PagingSource<Int, User>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {

        return try {
            val nextPage = params.key ?: 1
            val userList = userRepository.getUsers(
                page = nextPage,
                results = params.loadSize,
                seed = DEFAULT_SEED
            )
            LoadResult.Page(
                data = userList.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (userList.results.isEmpty()) null else nextPage + 1
            )
        }catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    /*override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val user = state.closestItemToPosition(anchorPosition) ?: return null
        return ensureValidKey(key = user.id - (state.config.pageSize / 2))
    }

     private fun ensureValidKey(key: Int) = max(STARTING_KEY, key)

    */

    override fun getRefreshKey(state: PagingState<Int, User>): Int?
    {
        return state.anchorPosition
    }



}