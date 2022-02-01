package com.sunny.sahayatribookingsewa.api

import com.sunny.sahayatribookingsewa.model.AdminTicket
import com.sunny.sahayatribookingsewa.model.BookingTicket
import com.sunny.sahayatribookingsewa.response.AddBookingResponse
import com.sunny.sahayatribookingsewa.response.GetAdminResponse
import com.sunny.sahayatribookingsewa.response.GetBookingResponse
import retrofit2.Response
import retrofit2.http.*

interface BookingAPI {

    @GET("bookingTicket")
    suspend fun getAllBookings(
        @Header("Authorization") token: String
    ): Response<GetBookingResponse>

    @GET("bookings")
    suspend fun getMyBookings(
        @Header("Authorization") token: String
    ): Response<GetBookingResponse>

    @POST("bookingTicket/insert")
    suspend fun insertBooking(
        @Header("Authorization") token: String,
        @Body bookingTicket: BookingTicket
    ): Response<AddBookingResponse>

    @DELETE("bookingTicket/delete/{id}")
    suspend fun deleteBooking(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Response<AddBookingResponse>

    @POST("adminTicket/insert")
    suspend fun insertTickets(
        @Header("Authorization") token: String,
        @Body adminTicket: AdminTicket
    ): Response<AddBookingResponse>

    @GET("adminTicket")
    suspend fun getTickets(
        @Header("Authorization") token: String
    ): Response<GetAdminResponse>


}