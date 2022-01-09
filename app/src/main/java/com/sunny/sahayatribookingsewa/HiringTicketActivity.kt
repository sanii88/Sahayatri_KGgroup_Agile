package com.sunny.sahayatribookingsewa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

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

        btnConfirmHiring.setOnClickListener {
            val intent = Intent(this, HiringActivity::class.java)
            startActivity(intent)
        }

    }
}