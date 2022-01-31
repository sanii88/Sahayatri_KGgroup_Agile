package com.sunny.sahayatribookingsewa.response

import com.sunny.sahayatribookingsewa.model.BookingTicket
import com.sunny.sahayatribookingsewa.model.User

data class GetUserResponse(
    val success: Boolean? = null,
    val data: MutableList<User>? = null,
    val message: String? = null,
)