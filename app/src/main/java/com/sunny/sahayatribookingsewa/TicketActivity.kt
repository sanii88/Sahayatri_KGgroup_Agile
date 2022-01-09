package com.sunny.sahayatribookingsewa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.sunny.sahayatribookingsewa.ui.home.HomeFragment

class TicketActivity : AppCompatActivity() {

    private lateinit var tvTicketRoute : TextView
    private lateinit var tvTicketDate : TextView
    private lateinit var tvTicketVehicleType : TextView
    private lateinit var tvTicketSeat : TextView
    private lateinit var tvTicketBoardingPoint : TextView
    private lateinit var tvTicketBoardingPerson : TextView
    private lateinit var tvTicketContact : TextView
    private lateinit var btnConfirmBooking : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket)

        tvTicketRoute = findViewById(R.id.tvTicketRoute)
        tvTicketDate = findViewById(R.id.tvTicketDate)
        tvTicketVehicleType = findViewById(R.id.tvTicketVehicleType)
        tvTicketSeat = findViewById(R.id.tvTicketSeat)
        tvTicketBoardingPoint = findViewById(R.id.tvTicketBoardingPoint)
        tvTicketBoardingPerson = findViewById(R.id.tvTicketBoardingPerson)
        tvTicketContact = findViewById(R.id.tvTicketContact)
        btnConfirmBooking = findViewById(R.id.btnConfirmBooking)

        btnConfirmBooking.setOnClickListener {
            val intent = Intent(this, MainDashboard::class.java)
            startActivity(intent)
        }

    }
}