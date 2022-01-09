package com.sunny.sahayatribookingsewa.repository

import com.sunny.sahayatribookingsewa.api.MyApiRequest
import com.sunny.sahayatribookingsewa.api.ServiceBuilder
import com.sunny.sahayatribookingsewa.api.UserAPI
import com.sunny.sahayatribookingsewa.model.User
import com.sunny.sahayatribookingsewa.response.UserResponse

class UserRepository : MyApiRequest(){

    private val userApi =
        ServiceBuilder.buildService(UserAPI::class.java)

    //Register user
    suspend fun registerUser(user: User): UserResponse {
        return apiRequest {
            userApi.registerUser(user)
        }
    }

    // Login user
    suspend fun loginUser(phone: String, password: String): UserResponse {
        return apiRequest {
            userApi.loginUser(phone, password)
        }
    }
}