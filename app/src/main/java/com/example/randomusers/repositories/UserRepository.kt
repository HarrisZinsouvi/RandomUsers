package com.example.randomusers.repositories
import com.example.randomusers.dto.UserResponse
import com.example.randomusers.models.User
import com.example.randomusers.services.UserService
import retrofit2.Retrofit

class UserRepository(instance: Retrofit) {

    private val userService : UserService = instance.create(UserService::class.java)

    suspend fun getUsers(page: Int, results: Int, seed: String): UserResponse {
        return userService.getUsers(page, results, seed)
    }
}