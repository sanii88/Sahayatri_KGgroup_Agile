package com.sunny.sahayatribookingsewa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class HiringActivity : AppCompatActivity() {

    private lateinit var btnHiring: Button
    private lateinit var tvVhType: TextView
    private lateinit var tvDays: TextView
    private lateinit var tvDate: TextView
    private lateinit var tvCalendar: TextView
    private lateinit var spVhType: Spinner
    private lateinit var spDays: Spinner
    private lateinit var etContactInfo: EditText

    private val vehicleType =
        arrayOf("Bus", "Micro", "Jeep")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hiring)

        btnHiring = findViewById(R.id.btnHiring)
        tvVhType = findViewById(R.id.tvVhType)
        tvDays = findViewById(R.id.tvDays)
        tvDate = findViewById(R.id.tvDate)
        tvCalendar = findViewById(R.id.tvCalendar)
        spVhType = findViewById(R.id.spVhType)
        spDays = findViewById(R.id.spDays)
        etContactInfo = findViewById(R.id.etContactInfo)

        btnHiring.setOnClickListener{
            val intent = Intent(this, HiringTicketActivity::class.java)
            startActivity(intent)
        }

        //Array Adapter
        val vehicleAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, vehicleType)
        val dayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, vehicleType)

        //Setting the adapter to spinner's adapter
        spVhType.adapter = vehicleAdapter
        spDays.adapter = dayAdapter

        //On item selected listener on spinner
        //for vehicle type
        spVhType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                Toast.makeText(this@HiringActivity,"Selected item : $selectedItem", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        //for hiring days
        spDays.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                Toast.makeText(this@HiringActivity,"Selected item : $selectedItem", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

    }
}