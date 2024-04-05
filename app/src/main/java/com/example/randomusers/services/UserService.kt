package com.example.randomusers.services

import com.example.randomusers.dto.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {
    @GET("/api/")
    suspend fun getUsers(@Query("page") page: Int, @Query("results") results: Int, @Query("seed") seed: String): UserResponse
}