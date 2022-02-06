package com.sunny.sahayatribookingsewa.repository

import com.sunny.sahayatribookingsewa.api.MyApiRequest
import com.sunny.sahayatribookingsewa.api.ServiceBuilder
import com.sunny.sahayatribookingsewa.api.UserAPI
import com.sunny.sahayatribookingsewa.model.User
import com.sunny.sahayatribookingsewa.response.*

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

    //Get Users
    suspend fun getAllUsers(): GetUserResponse {
        return apiRequest {
            userApi.getAllUsers(ServiceBuilder.token!!)
        }
    }

    //Delete Users
    suspend fun deleteUsers(id: String): AddBookingResponse {
        return apiRequest {
            userApi.deleteUsers(ServiceBuilder.token!!,id)
        }
    }

    //Update Users
    suspend fun updateUser(id: String, user: User): AddBookingResponse {
        return apiRequest {
            userApi.updateUser(
                ServiceBuilder.token!!, id, user
            )

        }
    }

    //Get my info
    suspend fun getMe(): MydetailsResponse {
        return apiRequest {
            userApi.getMe(ServiceBuilder.token!!)
        }
    }
}