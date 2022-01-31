package com.sunny.sahayatribookingsewa.response

import com.sunny.sahayatribookingsewa.model.AdminTicket
import com.sunny.sahayatribookingsewa.model.User

data class GetAdminResponse(
    val success: Boolean? = null,
    val data: MutableList<AdminTicket>? = null,
    val message: String? = null
)