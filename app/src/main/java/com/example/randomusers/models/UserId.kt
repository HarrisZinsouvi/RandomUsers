package com.example.randomusers.models

import android.os.Parcel
import android.os.Parcelable


data class UserId(
    val name: String,
    val value: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(value)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserId> {
        override fun createFromParcel(parcel: Parcel): UserId {
            return UserId(parcel)
        }

        override fun newArray(size: Int): Array<UserId?> {
            return arrayOfNulls(size)
        }
    }
}
