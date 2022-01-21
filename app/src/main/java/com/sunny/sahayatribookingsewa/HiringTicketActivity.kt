package com.sunny.sahayatribookingsewa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.sunny.sahayatribookingsewa.model.BookingTicket
import com.sunny.sahayatribookingsewa.model.Hiring
import com.sunny.sahayatribookingsewa.repository.BookingRepository
import com.sunny.sahayatribookingsewa.repository.HiringRepository
import com.sunny.sahayatribookingsewa.util.SavedData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class HiringTicketActivity : AppCompatActivity() {

    private lateinit var tvHireVehicleType : TextView
    private lateinit var tvHireDays : TextView
    private lateinit var tvHireDate : TextView
    private lateinit var tvHireContact : TextView
    private lateinit var btnConfirmHiring : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hiring_ticket)

        tvHireVehicleType = findViewById(R.id.tvHireVehicleType)
        tvHireDays = findViewById(R.id.tvHireDays)
        tvHireDate = findViewById(R.id.tvHireDate)
        tvHireContact = findViewById(R.id.tvHireContact)
        btnConfirmHiring = findViewById(R.id.btnConfirmHiring)

        val contact = SavedData.getSavedData("Contact")
        val hireDays = SavedData.getSavedData("HireDays")
        val date = SavedData.getSavedData("date")
        val vehicleType = SavedData.getSavedData("vehicleType")

        tvHireDate.text = date
        tvHireVehicleType.text = vehicleType
        tvHireContact.text = contact
        tvHireDays.text = hireDays

        val hiring = Hiring(
            departure_date = date,
            vehicle_type = vehicleType,
            contact = contact,
            hireDays =  hireDays
        )
        

        btnConfirmHiring.setOnClickListener {
           confirmHiring(hiring)
        }

    }

    private fun confirmHiring(hiring: Hiring) {

        CoroutineScope(Dispatchers.IO).launch {
            try {

                val hiringRepo = HiringRepository()
                val response = hiringRepo.insertHiring(hiring)
                if (response.success == true) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@HiringTicketActivity,
                            "Hiring Successful",
                            Toast.LENGTH_SHORT
                        ).show()


                        val intent = Intent(this@HiringTicketActivity, ViewHiringActivity::class.java)
                        startActivity(intent)
                    }
                }

            } catch (ex: IOException) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(applicationContext, ex.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}