package com.sunny.sahayatribookingsewa.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.PrimaryKey

data class BookingTicket(
//{
//    "route":"Kathmandu-Pokhara",
//    "vehicle_type":"Bus",
//    "departure_date":"2022-1-2",
//    "seatNo":"1",
//    "boarding_person":"sunny",
//    "boarding_point":"Buspark",
//    "contact":"1234"
//}
    var _id: String? = null,
    var route: String? = null,
    var vehicle_type: String? = null,
    var seatNo: String? = null,
    var departure_date: String? = null,
    var boarding_person: String? = null,
    var boarding_point: String? = null,
    var contact: String? = null
) : Parcelable{
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
        parcel.writeString(route)
        parcel.writeString(vehicle_type)
        parcel.writeString(seatNo)
        parcel.writeString(departure_date)
        parcel.writeString(boarding_person)
        parcel.writeString(boarding_point)
        parcel.writeString(contact)
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
