package com.example.randomusers.models

//user's date of birth
import android.os.Parcel
import android.os.Parcelable


data class UserDOB(
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

    override fun toString(): String {
        return "Naissance(date='$date', age=$age)"
    }

    companion object CREATOR : Parcelable.Creator<UserDOB> {
        override fun createFromParcel(parcel: Parcel): UserDOB {
            return UserDOB(parcel)
        }

        override fun newArray(size: Int): Array<UserDOB?> {
            return arrayOfNulls(size)
        }
    }
}
