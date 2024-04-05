package com.example.randomusers.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomusers.models.User
import com.example.randomusers.repositories.UserRepository
import com.example.randomusers.utils.Constants.Companion.DEFAULT_SEED
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val userRepository: UserRepository) : ViewModel() {
    val users: MutableStateFlow<List<User>> = MutableStateFlow(emptyList())
    var currentPage = 0
    val pageSize = 1
    private var isLastPage = false
    private var isLoading = false

    fun fetchUsers() {
        if (isLoading || isLastPage) return
        isLoading = true
        viewModelScope.launch {
            try {

                val res = userRepository.getUsers(currentPage, pageSize, DEFAULT_SEED)
                //Log.i("RESULT ",res.toString())
                if (res.results.isEmpty()) {
                    isLastPage = true
                } else {
                    users.value = users.value.orEmpty() + res.results
                    currentPage++
                }
            } catch (e: Exception) {
                e.printStackTrace()

            } finally {
                isLoading = false
            }
        }
    }
}


/*
*
*
class MainViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _pagingData = MutableStateFlow<PagingData<User>>(PagingData.empty())
    val pagingData: StateFlow<PagingData<User>> = _pagingData

    private var currentPage = 1
    private var isLastPage = false
    private var isLoading = false

    private val pagingConfig = PagingConfig(
        pageSize = 5,
        enablePlaceholders = false
    )

    fun fetchUsers() {
        if (isLoading || isLastPage) return
        isLoading = true

        viewModelScope.launch {
            try {
                val pagingSource = UserPagingSource(userRepository)
                val newPagingData = Pager(pagingConfig) {
                    pagingSource
                }.flow
                    .cachedIn(viewModelScope)

                newPagingData.collect {
                    _pagingData.value = it
                }

                currentPage++
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                isLoading = false
            }
        }
    }
}


* */