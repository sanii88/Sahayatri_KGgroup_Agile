package com.sunny.sahayatribookingsewa.ui.home

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sunny.sahayatribookingsewa.BoardingActivity
import com.sunny.sahayatribookingsewa.HiringActivity
import com.sunny.sahayatribookingsewa.R
import com.sunny.sahayatribookingsewa.databinding.FragmentHomeBinding
import com.sunny.sahayatribookingsewa.util.SavedData
import java.util.*

class HomeFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    val price: Int = 300
    private lateinit var btnBooking: Button
    private lateinit var btnHire: Button
    private lateinit var tvCalendar: TextView
    private lateinit var tvTotalPrice: TextView

    private val route =
        arrayListOf<String>(
            "Select none",
            "Kathmandu-Pokhara",
            "Kathmandu-Chitwan",
            "Kathmandu-Butwal",
            "Kathmandu-Lumbini"
        )
    private lateinit var spRoute: Spinner

    private var vehicleType = arrayListOf<String>("Bus", "Micro", "Jeep")
    private lateinit var spVehicleType: Spinner

    private val seat = arrayListOf<Int>(1,2,3,4)
    private lateinit var spSeat: Spinner

    companion object {
        var routes: String? = null
        var date: String? = null
        var vehicle_type: String? = null
        var seatNo: Int? = null
        val total: Float? = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        spRoute = root.findViewById(R.id.spRoute)
        tvCalendar = root.findViewById(R.id.tvCalendar)
        spVehicleType = root.findViewById(R.id.spVehicleType)
        spSeat = root.findViewById(R.id.spSeat)
        btnHire = root.findViewById(R.id.btnHire)
        tvTotalPrice = root.findViewById(R.id.tvTotalPrice)


        btnBooking = root.findViewById(R.id.btnBooking)
        btnBooking.setOnClickListener {

//            SavedData.route = spRoute.selectedItem.toString()
//            SavedData.seatNo = spSeat.selectedItem as Int
//            SavedData.vehicleType = spVehicleType.selectedItem.toString()

             saveBooking()
        }

        btnHire.setOnClickListener {
            val intent = Intent(view?.context, HiringActivity::class.java)
            startActivity(intent)
        }

        //date
        tvCalendar.setOnClickListener {
            loadCalendar()
        }

        //Array Adapter
        val vehicleAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, vehicleType)
        val routeAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, route)
        val seatAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, seat)

        //Setting the adapter to spinner's adapter
        spVehicleType.adapter = vehicleAdapter
        spRoute.adapter = routeAdapter
        spSeat.adapter = seatAdapter

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
                Toast.makeText(
                    requireContext(),
                    "Selected item : $vehicle_type",
                    Toast.LENGTH_SHORT
                ).show()
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
                routes = parent?.getItemAtPosition(position).toString()
                Toast.makeText(
                    requireContext(),
                    "Selected item : $routes",
                    Toast.LENGTH_SHORT
                ).show()
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
                Toast.makeText(
                    requireContext(),
                    "Selected item : $seatNo",
                    Toast.LENGTH_SHORT
                ).show()
//                val seatNo = parent?.getItemAtPosition(position).toString()

                var bus : Int = 300
                var micro : Int = 400
                var jeep : Int = 500
                var total = 0

                val noOfSeat= spSeat.selectedItem.toString().toInt()

                if(spVehicleType.selectedItem.toString().equals("Bus"))
                    total = bus * noOfSeat
                else if(spVehicleType.selectedItem.toString().equals("Micro"))
                    total = micro * noOfSeat
                else if(spVehicleType.selectedItem.toString().equals("Jeep"))
                    total = jeep * noOfSeat

                tvTotalPrice.text = total.toString()


            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }


        return root
    }

    private fun saveBooking() {

        SavedData.setData("routes", routes.toString())
        SavedData.setData("date", date.toString())
        SavedData.setData("vehicleType", vehicle_type.toString())
        SavedData.setData("seatNo", seatNo.toString())

            val intent = Intent(view?.context, BoardingActivity::class.java)
            startActivity(intent)
    }

    private fun loadCalendar() {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(), { view, year, monthOfYear, dayOfMonth ->
                date = "$dayOfMonth/$monthOfYear/$year"
                tvCalendar.text = date
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        TODO("Not yet implemented")
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

}