package com.sunny.sahayatribookingsewa.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.PrimaryKey

data class BookingTicket(
    var _id: String? = null,
    var route: String? = null,
    var vehicle_type: String? = null,
    var seatNo: String? = null,
    var departure_date: String? = null,
    var boarding_person: String? = null,
    var boarding_point: String? = null,
    var contact: String? = null,
    var price: String? = null,
    var createdAt: String? = null

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
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
        parcel.writeString(route)
        parcel.writeString(vehicle_type)
        parcel.writeString(seatNo)
        parcel.writeString(departure_date)
        parcel.writeString(boarding_person)
        parcel.writeString(boarding_point)
        parcel.writeString(contact)
        parcel.writeString(price)
        parcel.writeString(createdAt.toString())
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BookingTicket> {
        override fun createFromParcel(parcel: Parcel): BookingTicket {
            return BookingTicket(parcel)
        }

        override fun newArray(size: Int): Array<BookingTicket?> {
            return arrayOfNulls(size)
        }
    }
}