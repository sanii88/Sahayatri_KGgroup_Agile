package com.sunny.sahayatribookingsewa.model

import android.os.Parcel
import android.os.Parcelable

data class Hiring(
    var _id: String? = null,
    var vehicle_type: String? = null,
    var departure_date: String? = null,
    var hireDays: String? = null,
    var contact: String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(_id)
        parcel.writeString(vehicle_type)
        parcel.writeString(departure_date)
        parcel.writeString(hireDays)
        parcel.writeString(contact)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Hiring> {
        override fun createFromParcel(parcel: Parcel): Hiring {
            return Hiring(parcel)
        }

        override fun newArray(size: Int): Array<Hiring?> {
            return arrayOfNulls(size)
        }
    }
}
