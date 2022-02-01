package com.sunny.sahayatribookingsewa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.sunny.sahayatribookingsewa.model.BookingTicket
import com.sunny.sahayatribookingsewa.repository.BookingRepository
import com.sunny.sahayatribookingsewa.ui.home.HomeFragment
import com.sunny.sahayatribookingsewa.util.SavedData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.lang.Exception

class BookingTicketActivity : AppCompatActivity() {

    private lateinit var tvTicketRoute: TextView
    private lateinit var tvTicketDate: TextView
    private lateinit var tvTicketVehicleType: TextView
    private lateinit var tvTicketSeat: TextView
    private lateinit var tvTicketBoardingPoint: TextView
    private lateinit var tvTicketBoardingPerson: TextView
    private lateinit var tvTicketContact: TextView
    private lateinit var btnConfirmBooking: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket)
        supportActionBar?.hide()


        tvTicketRoute = findViewById(R.id.tvTicketRoute)
        tvTicketDate = findViewById(R.id.tvTicketDate)
        tvTicketVehicleType = findViewById(R.id.tvTicketVehicleType)
        tvTicketSeat = findViewById(R.id.tvTicketSeat)
        tvTicketBoardingPoint = findViewById(R.id.tvTicketBoardingPoint)
        tvTicketBoardingPerson = findViewById(R.id.tvTicketBoardingPerson)
        tvTicketContact = findViewById(R.id.tvTicketContact)
        btnConfirmBooking = findViewById(R.id.btnConfirmBooking)

        val contact = SavedData.getSavedData("myContact")
        val name = SavedData.getSavedData("myName")
        val route = SavedData.getSavedData("routes")
        val date = SavedData.getSavedData("date")
        val vehicleType = SavedData.getSavedData("vehicleType")
        val seatNo = SavedData.getSavedData("seatNo")
        val boardingPoint = SavedData.getSavedData("myBoardingPoint")


        tvTicketRoute.text = route
        tvTicketDate.text = date
        tvTicketBoardingPoint.text = boardingPoint
        tvTicketVehicleType.text = vehicleType
        tvTicketSeat.text = seatNo
        tvTicketBoardingPerson.text = name
        tvTicketContact.text = contact

        val ticket = BookingTicket(
            route = route,
            departure_date = date,
            boarding_point = boardingPoint,
            vehicle_type = vehicleType,
            seatNo = seatNo,
            boarding_person = name,
            contact = contact
        )
        btnConfirmBooking.setOnClickListener {

            confirmBooking(ticket)
        }
    }

    private fun confirmBooking(ticket: BookingTicket) {

        CoroutineScope(Dispatchers.IO).launch {
            try {

                val ticketRepo = BookingRepository()
                val response = ticketRepo.insertBooking(ticket)
//                withContext(Main) {
//                    Toast.makeText(
//                        this@BookingTicketActivity,
//                        response.message,
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }


                    if (response.success == true) {
                    withContext(Main) {
                        Toast.makeText(
                            this@BookingTicketActivity,
                            "Booking Successful",
                            Toast.LENGTH_SHORT
                        ).show()


                        val intent = Intent(this@BookingTicketActivity, MainDashboard::class.java)
                        startActivity(intent)
                    }
                }

            } catch (ex: Exception) {
                withContext(Main) {
                    Toast.makeText(applicationContext, ex.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}