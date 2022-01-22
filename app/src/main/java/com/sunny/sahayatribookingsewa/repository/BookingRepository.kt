package com.sunny.sahayatribookingsewa.repository

import com.sunny.sahayatribookingsewa.api.BookingAPI
import com.sunny.sahayatribookingsewa.api.MyApiRequest
import com.sunny.sahayatribookingsewa.api.ServiceBuilder
import com.sunny.sahayatribookingsewa.model.BookingTicket
import com.sunny.sahayatribookingsewa.response.AddBookingResponse
import com.sunny.sahayatribookingsewa.response.GetBookingResponse

class BookingRepository : MyApiRequest() {
    private val bookingApi = ServiceBuilder.buildService(BookingAPI::class.java)

    //Insert Bookings
    suspend fun insertBooking(bookingTicket: BookingTicket): AddBookingResponse {
        return apiRequest {
            bookingApi.insertBooking(ServiceBuilder.token!!,bookingTicket)
        }
    }

    //Get Bookings
    suspend fun getAllBookings(): GetBookingResponse {
        return apiRequest {
            bookingApi.getAllBookings(ServiceBuilder.token!!)
        }
    }

    //Delete Bookings
    suspend fun deleteBooking(id: String): AddBookingResponse {
        return apiRequest {
            bookingApi.deleteBooking(ServiceBuilder.token!!,id)
        }
    }

}