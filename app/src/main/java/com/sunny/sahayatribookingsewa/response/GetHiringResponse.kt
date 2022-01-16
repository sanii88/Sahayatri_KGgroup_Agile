package com.sunny.sahayatribookingsewa.response

import com.sunny.sahayatribookingsewa.model.BookingTicket
import com.sunny.sahayatribookingsewa.model.Hiring

data class GetHiringResponse(
    val success: Boolean? = null,
    val data: MutableList<Hiring>? = null,
    val message: String? = null
)