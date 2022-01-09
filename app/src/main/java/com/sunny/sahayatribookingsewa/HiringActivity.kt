package com.sunny.sahayatribookingsewa

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import java.util.*

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

    private val hireDays =
        arrayOf("1", "2", "3")

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

        //date
        tvCalendar.setOnClickListener{
            loadCalendar()
        }

        //Array Adapter
        val vehicleAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, vehicleType)
        val dayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, hireDays)

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

    private fun loadCalendar() {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this, {
                    view, year, monthOfYear , dayOfMonth ->
                tvCalendar.text = "$dayOfMonth/$monthOfYear/$year"
            },
            year,
            month,
            day
        )
        datePickerDialog.show()    }
}