package com.sunny.sahayatribookingsewa.api

import com.sunny.sahayatribookingsewa.model.User
import com.sunny.sahayatribookingsewa.response.*
import retrofit2.Response
import retrofit2.http.*

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

    @GET("users/show")
    suspend fun getAllUsers(
        @Header("Authorization") token: String
    ): Response<GetUserResponse>

    @DELETE("users/delete/{id}")
    suspend fun deleteUsers(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Response<AddBookingResponse>

    @PUT("users/update/{id}")
    suspend fun updateUser(
        @Header("Authorization")token:String,
        @Path("id")id:String,
        @Body user: User
    ):Response<AddBookingResponse>

    @GET("/users/getMe")
    suspend fun getMe(
        @Header("Authorization") token: String
    ): Response<MydetailsResponse>
}