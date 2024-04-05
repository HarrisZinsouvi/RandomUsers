package com.example.randomusers.models


import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class Timezone(
    val offset: String,
    val description: String
) : Parcelable, Serializable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(offset)
        parcel.writeString(description)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Timezone> {
        override fun createFromParcel(parcel: Parcel): Timezone {
            return Timezone(parcel)
        }

        override fun newArray(size: Int): Array<Timezone?> {
            return arrayOfNulls(size)
        }
    }
}
