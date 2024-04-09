package com.example.randomusers.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.randomusers.models.User
import com.example.randomusers.repositories.UserRepository
import com.example.randomusers.ui.lib.UserPagingSource
import com.example.randomusers.utils.Constants.Companion.DEFAULT_PAGE_SIZE
import kotlinx.coroutines.flow.Flow

class MainViewModel(private val userRepository: UserRepository) : ViewModel() {

        val users: Flow<PagingData<User>> = Pager(PagingConfig(pageSize = DEFAULT_PAGE_SIZE)) {
            UserPagingSource(userRepository)
        }.flow.cachedIn(viewModelScope)

}

