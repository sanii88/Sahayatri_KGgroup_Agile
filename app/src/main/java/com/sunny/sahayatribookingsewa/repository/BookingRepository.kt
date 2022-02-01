package com.sunny.sahayatribookingsewa.repository

import com.sunny.sahayatribookingsewa.api.BookingAPI
import com.sunny.sahayatribookingsewa.api.MyApiRequest
import com.sunny.sahayatribookingsewa.api.ServiceBuilder
import com.sunny.sahayatribookingsewa.model.AdminTicket
import com.sunny.sahayatribookingsewa.model.BookingTicket
import com.sunny.sahayatribookingsewa.response.AddBookingResponse
import com.sunny.sahayatribookingsewa.response.GetAdminResponse
import com.sunny.sahayatribookingsewa.response.GetBookingResponse
import retrofit2.http.Body

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

    //Get My Bookings
    suspend fun getMyBookings(): GetBookingResponse {
        return apiRequest {
            bookingApi.getMyBookings(ServiceBuilder.token!!)
        }
    }

    //Delete Bookings
    suspend fun deleteBooking(id: String): AddBookingResponse {
        return apiRequest {
            bookingApi.deleteBooking(ServiceBuilder.token!!,id)
        }
    }

    //Insert Tickets
    suspend fun insertTickets(adminTicket: AdminTicket): AddBookingResponse {
        return apiRequest {
            bookingApi.insertTickets(ServiceBuilder.token!!,adminTicket)
        }
    }

    //Get Admin Tickets
    //Get Bookings
    suspend fun getTickets(): GetAdminResponse {
        return apiRequest {
            bookingApi.getTickets(ServiceBuilder.token!!)
        }
    }

}