package com.sunny.sahayatribookingsewa.api

import com.sunny.sahayatribookingsewa.model.BookingTicket
import com.sunny.sahayatribookingsewa.model.Hiring
import com.sunny.sahayatribookingsewa.response.AddBookingResponse
import com.sunny.sahayatribookingsewa.response.AddHiringResponse
import com.sunny.sahayatribookingsewa.response.GetBookingResponse
import com.sunny.sahayatribookingsewa.response.GetHiringResponse
import retrofit2.Response
import retrofit2.http.*

interface HiringAPI {

    @GET("hiring")
    suspend fun getAllHiring(
        @Header("Authorization") token: String
    ): Response<GetHiringResponse>

    @POST("hiring/insert")
    suspend fun insertHiring(
        @Header("Authorization") token: String,
        @Body hiring: Hiring
    ): Response<AddHiringResponse>

    @DELETE("hiring/delete/{id}")
    suspend fun deleteHiring(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Response<AddHiringResponse>

}