package com.example.randomusers.models

import android.os.Parcel
import android.os.Parcelable

data class Registration(
    val date: String,
    val age: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(date)
        parcel.writeInt(age)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Registration> {
        override fun createFromParcel(parcel: Parcel): Registration {
            return Registration(parcel)
        }

        override fun newArray(size: Int): Array<Registration?> {
            return arrayOfNulls(size)
        }
    }
}
