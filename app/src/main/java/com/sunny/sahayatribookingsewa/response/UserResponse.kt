package com.sunny.sahayatribookingsewa.response

import com.sunny.sahayatribookingsewa.model.User

data class UserResponse(
    val success: Boolean? = null,
    val token: String? = null,
    val message: String? = null,
    val user: User? = null,
    val userType: String? = null

)