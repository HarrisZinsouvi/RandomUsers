package com.example.randomusers.models

import android.os.Parcel
import android.os.Parcelable

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
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(UserName::class.java.classLoader)!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readParcelable(Location::class.java.classLoader),
        parcel.readParcelable(UserLogin::class.java.classLoader),
        parcel.readParcelable(UserDOB::class.java.classLoader),
        parcel.readParcelable(Registration::class.java.classLoader),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readParcelable(UserId::class.java.classLoader),
        parcel.readParcelable(ProfilePicture::class.java.classLoader),
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(name, flags)
        parcel.writeString(email)
        parcel.writeString(gender)
        parcel.writeParcelable(location, flags)
        parcel.writeParcelable(login, flags)
        parcel.writeParcelable(dob, flags)
        parcel.writeParcelable(registered, flags)
        parcel.writeString(phone)
        parcel.writeString(cell)
        parcel.writeParcelable(id, flags)
        parcel.writeParcelable(picture, flags)
        parcel.writeString(nat)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}
