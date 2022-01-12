package com.sunny.sahayatribookingsewa.model

data class BookingTicket(
    var _id: String? = null,
    var route: String? = null,
    var vehicle_type: String? = null,
    var seatNo: String? = null,
    var departure_date: String? = null,
    var boarding_person: String? = null,
    var boarding_point: String? = null,
    var contact: String? = null,
)
