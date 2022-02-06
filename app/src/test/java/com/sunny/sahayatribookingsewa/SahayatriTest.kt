package com.sunny.sahayatribookingsewa

import com.sunny.sahayatribookingsewa.api.ServiceBuilder
import com.sunny.sahayatribookingsewa.model.BookingTicket
import com.sunny.sahayatribookingsewa.model.Hiring
import com.sunny.sahayatribookingsewa.model.User
import com.sunny.sahayatribookingsewa.repository.BookingRepository
import com.sunny.sahayatribookingsewa.repository.HiringRepository
import com.sunny.sahayatribookingsewa.repository.UserRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class SahayatriTest {

    private lateinit var userRepository: UserRepository
    private lateinit var bookingRepository: BookingRepository
    private lateinit var hiringRepository: HiringRepository


    //Login
    @Test
    fun checkLogin() = runBlocking {
        userRepository = UserRepository()
        val response = userRepository.loginUser("12345", "sunny")
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun checkLoginWithWrongDetails() = runBlocking {
        userRepository = UserRepository()
        val response = userRepository.loginUser("78788956", "sunn")
        val expectedResult = false
        val actualResult = response.success
        Assert.assertEquals(expectedResult, actualResult)
    }


    //Register
    @Test
    fun checkRegisterUser() = runBlocking {
        val user = User(
            username = "sunny",
            phone = "12345",
            email = "sani@gmail.com",
            address = "ktm",
            password = "sunny123"
        )
        userRepository = UserRepository()
        val response = userRepository.registerUser(user)
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult, actualResult)
    }

    //Get All Users
    @Test
    fun checkGetAllUsers() = runBlocking {
        userRepository = UserRepository()
        bookingRepository = BookingRepository()

        ServiceBuilder.token = "Bearer " + userRepository.loginUser("12345" , "sunny").token
        val expectedResult = true
        val actualResult = userRepository.getAllUsers().success
        Assert.assertEquals(expectedResult, actualResult)
    }


    //Insert ticket
    @Test
    fun checkAddTicket() = runBlocking {
        userRepository = UserRepository()
        hiringRepository = HiringRepository()

        val hiring = Hiring(
            vehicle_type = "Bus",
            departure_date = "5/2/2022",
            hireDays = "3",
            contact = "12345"
        )

        ServiceBuilder.token = "Bearer " + userRepository.loginUser("12345" , "sunny").token
        val expectedResult = true
        val actualResult = hiringRepository.insertHiring(hiring).success
        Assert.assertEquals(expectedResult, actualResult)
    }

    //Insert hiring
    @Test
    fun checkAddHiring() = runBlocking {
        userRepository = UserRepository()
        bookingRepository = BookingRepository()

        val ticket = BookingTicket(
            route = "Kathmandu-Pokhara",
            vehicle_type = "Bus",
            seatNo = "3",
            departure_date = "5/2/2022",
            boarding_person = "sunny",
            boarding_point = "Buspark",
            contact = "12345"
        )

        ServiceBuilder.token = "Bearer " + userRepository.loginUser("12345" , "sunny").token
        val expectedResult = true
        val actualResult = bookingRepository.insertBooking(ticket).success
        Assert.assertEquals(expectedResult, actualResult)
    }


    //Get All Booking Tickets
    @Test
    fun checkGetAllBooking() = runBlocking {
        userRepository = UserRepository()
        bookingRepository = BookingRepository()

        ServiceBuilder.token = "Bearer " + userRepository.loginUser("12345" , "sunny").token
        val expectedResult = true
        val actualResult = bookingRepository.getAllBookings().success
        Assert.assertEquals(expectedResult, actualResult)
    }

    //Get All Hirings
    @Test
    fun checkGetAllHiring() = runBlocking {
        userRepository = UserRepository()
        hiringRepository = HiringRepository()

        ServiceBuilder.token = "Bearer " + userRepository.loginUser("12345" , "sunny").token
        val expectedResult = true
        val actualResult = hiringRepository.getAllHiring().success
        Assert.assertEquals(expectedResult, actualResult)
    }


    // User Profile
    @Test
    fun checkUserProfile() = runBlocking {
        userRepository = UserRepository()
        ServiceBuilder.token = "Bearer " + userRepository.loginUser("12345","sunny").token
        val response = userRepository.getMe()
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult,actualResult)
    }
}