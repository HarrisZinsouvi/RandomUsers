package com.example.randomusers.models

import android.os.Parcel
import android.os.Parcelable


data class ProfilePicture(
    val large: String,
    val medium: String="",
    val thumbnail: String=""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(large)
        parcel.writeString(medium)
        parcel.writeString(thumbnail)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProfilePicture> {
        override fun createFromParcel(parcel: Parcel): ProfilePicture {
            return ProfilePicture(parcel)
        }

        override fun newArray(size: Int): Array<ProfilePicture?> {
            return arrayOfNulls(size)
        }
    }
}
