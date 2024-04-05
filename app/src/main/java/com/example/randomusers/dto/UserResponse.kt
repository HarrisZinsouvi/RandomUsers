package com.example.randomusers.dto

import com.example.randomusers.models.User

data class UserResponse(
    val results: List<User>,
    val info: Info
)

data class Info(
    val seed: String,
    val results: Int,
    val page: Int,
    val version: String
)
