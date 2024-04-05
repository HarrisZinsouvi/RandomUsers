package com.example.randomusers.models

data class User(
    val name: UserName,
    val email: String,
    val gender: String="",
    val location: Location?=null,
    val login: UserLogin?=null,
    val dob: UserDOB?=null,
    val registered: Registration?=null,
    val phone: String="",
    val cell: String="",
    val id: UserId?=null,
    val picture:ProfilePicture?=null,
    val nat: String=""
)