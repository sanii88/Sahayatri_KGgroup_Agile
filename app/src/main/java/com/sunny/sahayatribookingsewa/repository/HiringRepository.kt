package com.sunny.sahayatribookingsewa.repository

import com.sunny.sahayatribookingsewa.api.BookingAPI
import com.sunny.sahayatribookingsewa.api.HiringAPI
import com.sunny.sahayatribookingsewa.api.MyApiRequest
import com.sunny.sahayatribookingsewa.api.ServiceBuilder
import com.sunny.sahayatribookingsewa.model.BookingTicket
import com.sunny.sahayatribookingsewa.model.Hiring
import com.sunny.sahayatribookingsewa.response.AddBookingResponse
import com.sunny.sahayatribookingsewa.response.AddHiringResponse
import com.sunny.sahayatribookingsewa.response.GetBookingResponse
import com.sunny.sahayatribookingsewa.response.GetHiringResponse

class HiringRepository : MyApiRequest(){

    private val hiringApi = ServiceBuilder.buildService(HiringAPI::class.java)

    //Insert Hiring
    suspend fun insertHiring(hiring: Hiring): AddHiringResponse {
        return apiRequest {
            hiringApi.insertHiring(ServiceBuilder.token!!,hiring)
        }
    }

    //Get Hiring
    suspend fun getAllHiring(): GetHiringResponse {
        return apiRequest {
            hiringApi.getAllHiring(ServiceBuilder.token!!)
        }
    }

    //Delete Hiring
    suspend fun deleteHiring(id: String): AddHiringResponse {
        return apiRequest {
            hiringApi.deleteHiring(ServiceBuilder.token!!,id)
        }
    }

}