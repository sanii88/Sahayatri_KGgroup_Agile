package com.sunny.sahayatribookingsewa.response

import com.sunny.sahayatribookingsewa.model.BookingTicket

data class GetBookingResponse(
    val success: Boolean? = null,
    val data: MutableList<BookingTicket>? = null,
    val message: String? = null,
)