package com.sunny.sahayatribookingsewa.api

import com.sunny.sahayatribookingsewa.model.User
import com.sunny.sahayatribookingsewa.response.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UserAPI {

    //Register user
    @POST("users/register")
    suspend fun registerUser(
        @Body user: User
    ): Response<UserResponse>

    //Login user
    @FormUrlEncoded
    @POST("users/login")
    suspend fun loginUser(
        @Field("phone") phone: String,
        @Field("password") password: String
    ): Response<UserResponse>

}