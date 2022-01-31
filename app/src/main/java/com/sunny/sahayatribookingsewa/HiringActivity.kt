package com.sunny.sahayatribookingsewa

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import com.sunny.sahayatribookingsewa.ui.home.HomeFragment
import com.sunny.sahayatribookingsewa.util.SavedData
import java.util.*

class HiringActivity : AppCompatActivity() {

    private lateinit var btnHiring: Button
    private lateinit var btnBooking1: Button
    private lateinit var tvCalendar: TextView
    private lateinit var spVhType: Spinner
    private lateinit var etHireDays: EditText
    private lateinit var etContactInfo: EditText

    private val vehicleType =
        arrayListOf<String>("Bus", "Micro", "Jeep")

    companion object {
        var vehicle_type: String? = null
        var date: String? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hiring)

        btnHiring = findViewById(R.id.btnHiring)
        btnBooking1 = findViewById(R.id.btnBooking1)
        tvCalendar = findViewById(R.id.tvCalendar)
        spVhType = findViewById(R.id.spVhType)
        etHireDays = findViewById(R.id.etHireDays)
        etContactInfo = findViewById(R.id.etContactInfo)

        btnBooking1.setOnClickListener {
            val intent = Intent(this, MainDashboard::class.java)
            startActivity(intent)
        }

        btnHiring.setOnClickListener {

            if (TextUtils.isEmpty(etHireDays.text)) {
                etHireDays.error = "Hire days must not be empty!!"
                etHireDays.requestFocus()
                return@setOnClickListener
            } else if (TextUtils.isEmpty(etContactInfo.text)) {
                etContactInfo.error = "Contact must not be empty!!"
                etContactInfo.requestFocus()
                return@setOnClickListener
            }

            saveHiring()
        }

        //date
        tvCalendar.setOnClickListener {
            loadCalendar()
        }

        //Array Adapter
        val vehicleAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, vehicleType)

        //Setting the adapter to spinner's adapter
        spVhType.adapter = vehicleAdapter

        //On item selected listener on spinner
        //for vehicle type
        spVhType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                vehicle_type = parent?.getItemAtPosition(position).toString()
                Toast.makeText(
                    this@HiringActivity,
                    "Selected item : $vehicle_type",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

    }

    private fun saveHiring() {
        val contact = etContactInfo.text.toString()
        val hireDays = etHireDays.text.toString()

        SavedData.setData("HireDays", hireDays)
        SavedData.setData("Contact", contact)
        SavedData.setData("date", date.toString())
        SavedData.setData("vehicleType", vehicle_type.toString())

        val intent = Intent(this, HiringTicketActivity::class.java)
        startActivity(intent)

    }

    private fun loadCalendar() {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this, { view, year, monthOfYear, dayOfMonth ->
                val monthOfYear = monthOfYear + 1
                date = "$dayOfMonth/$monthOfYear/$year"
                tvCalendar.text = date
            },
            year,
            month,
            day
        )
        datePickerDialog.datePicker.minDate = System.currentTimeMillis() - 1000;
        datePickerDialog.show()
    }
}