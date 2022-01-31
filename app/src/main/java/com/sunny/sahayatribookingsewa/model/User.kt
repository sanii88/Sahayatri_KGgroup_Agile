package com.sunny.sahayatribookingsewa.model

import android.os.Parcel
import android.os.Parcelable

data class User(
    var _id:String? = "",
    var username: String? = null,
    var email:String?=null,
    var address:String?=null,
    var phone: String? = null,
    var password:String? = null,
    var profile_pic:String?= null,
    var userType:String?=null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(_id)
        parcel.writeString(username)
        parcel.writeString(email)
        parcel.writeString(address)
        parcel.writeString(phone)
        parcel.writeString(password)
        parcel.writeString(profile_pic)
        parcel.writeString(userType)
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
