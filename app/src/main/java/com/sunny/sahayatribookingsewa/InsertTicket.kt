package com.sunny.sahayatribookingsewa

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.sunny.sahayatribookingsewa.model.AdminTicket
import com.sunny.sahayatribookingsewa.repository.BookingRepository
import com.sunny.sahayatribookingsewa.ui.dashboard.DashboardFragment
import com.sunny.sahayatribookingsewa.ui.home.HomeFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.util.*

class InsertTicket : AppCompatActivity() {

    private lateinit var btnBooking: Button
    private lateinit var tvCalendar: TextView
    private lateinit var tvTotalPrice: TextView
    private lateinit var spRoute: Spinner
    private lateinit var spVehicleType: Spinner
    private lateinit var spSeat: Spinner
    private lateinit var spBoarding: Spinner

    private var routes =
        arrayListOf<String>(
            "Select none",
            "Kathmandu-Pokhara",
            "Kathmandu-Chitwan",
            "Kathmandu-Butwal",
            "Kathmandu-Lumbini"
        )

    private var vehicleType = arrayListOf<String>("Bus", "Micro", "Jeep")

    private val seat = arrayListOf<Int>(1, 2, 3, 4)

    private val boardingPoint =
        arrayOf("Koteshwor", "Buspark", "Kalanki")

    companion object {
        var route: String? = null
        var vehicle_type: String? = null
        var seatNo: Int? = null
        var date: String? = null
        var boarding_point: String? = null
        var price: String? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_ticket)

        btnBooking = findViewById(R.id.btnBooking)
        tvCalendar = findViewById(R.id.tvCalendar)
        tvTotalPrice = findViewById(R.id.tvTotalPrice)
        spRoute = findViewById(R.id.spRoute)
        spVehicleType = findViewById(R.id.spVehicleType)
        spSeat = findViewById(R.id.spSeat)
        spBoarding = findViewById(R.id.spBoarding)

        btnBooking.setOnClickListener {
            saveBooking()
        }

        //date
        tvCalendar.setOnClickListener {
            loadCalendar()
        }


        //Array Adapter
        val vehicleAdapter =
            ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, vehicleType)
        val routeAdapter =
            ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, routes)
        val seatAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, seat)
        val boardingAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, boardingPoint)

        //Setting the adapter to spinner's adapter
        spVehicleType.adapter = vehicleAdapter
        spRoute.adapter = routeAdapter
        spSeat.adapter = seatAdapter
        spBoarding.adapter = boardingAdapter

        //On item selected listener on spinner
        //for vehicle type
        spVehicleType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                vehicle_type = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }


        //for route
        spRoute.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                route = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        //for seat
        spSeat.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                seatNo = parent?.getItemAtPosition(position).toString().toInt()

                var bus: Int = 300
                var micro: Int = 400
                var jeep: Int = 500
                var total = 0

                val noOfSeat = spSeat.selectedItem.toString().toInt()

                if (spVehicleType.selectedItem.toString().equals("Bus"))
                    total = bus * noOfSeat
                else if (spVehicleType.selectedItem.toString().equals("Micro"))
                    total = micro * noOfSeat
                else if (spVehicleType.selectedItem.toString().equals("Jeep"))
                    total = jeep * noOfSeat

                tvTotalPrice.text = total.toString()


            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
        //for boarding point
        spBoarding.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                boarding_point = parent?.getItemAtPosition(position).toString()
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

    private fun saveBooking() {
        val ticket = AdminTicket(
            route = route,
            vehicle_type = vehicle_type,
            seatNo = seatNo.toString(),
            departure_date = date,
            boarding_point = boarding_point,
            price = tvTotalPrice.text.toString()
        )

        CoroutineScope(Dispatchers.IO).launch {
            try {

                val ticketRepo = BookingRepository()
                val response = ticketRepo.insertTickets(ticket)
                if (response.success == true) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@InsertTicket,
                            "Ticket inserted!!",
                            Toast.LENGTH_SHORT
                        ).show()


                        val intent =
                            Intent(this@InsertTicket, MainDashboard::class.java)
                        startActivity(intent)
                    }
                }

            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(applicationContext, ex.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}