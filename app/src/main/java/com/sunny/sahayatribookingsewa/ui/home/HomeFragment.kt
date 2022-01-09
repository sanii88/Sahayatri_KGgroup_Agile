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
import java.util.*

class HomeFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var btnBooking: Button
    private lateinit var btnHire: Button
    private lateinit var tvRoute: TextView
    private lateinit var tvDate: TextView
    private lateinit var tvVehicleType: TextView
    private lateinit var tvSeat: TextView
    private lateinit var tvCalendar: TextView

    private val route =
        arrayOf("Select none" , "Kathmandu-Pokhara", "Kathmandu-Chitwan", "Kathmandu-Butwal", "Kathmandu-Lumbini")
    private lateinit var spRoute: Spinner

    private val vehicleType = arrayOf("Bus", "Micro", "Jeep")
    private lateinit var spVehicleType: Spinner

    private val seat = arrayOf("1", "2", "3", "4")
    private lateinit var spSeat: Spinner

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        tvRoute = root.findViewById(R.id.tvRoute)
        spRoute = root.findViewById(R.id.spRoute)
        tvDate = root.findViewById(R.id.tvDate)
        tvCalendar = root.findViewById(R.id.tvCalendar)
        tvVehicleType = root.findViewById(R.id.tvVehicleType)
        spVehicleType = root.findViewById(R.id.spVehicleType)
        tvSeat = root.findViewById(R.id.tvSeat)
        spSeat = root.findViewById(R.id.spSeat)
        btnHire = root.findViewById(R.id.btnHire)

        btnBooking = root.findViewById(R.id.btnBooking)
        btnBooking.setOnClickListener {
            val intent = Intent(view?.context, BoardingActivity::class.java)
            startActivity(intent)
        }

        btnHire.setOnClickListener {
            val intent = Intent(view?.context, HiringActivity::class.java)
            startActivity(intent)
        }

        //date
        tvCalendar.setOnClickListener{
            loadCalendar()
        }

        //Array Adapter
        val vehicleAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, vehicleType)
        val routeAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, route)
        val seatAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, seat)

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
                val selectedItem = parent?.getItemAtPosition(position).toString()
                Toast.makeText(requireContext(),"Selected item : $selectedItem",Toast.LENGTH_SHORT).show()
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
                val selectedItem = parent?.getItemAtPosition(position).toString()
                Toast.makeText(requireContext(),"Selected item : $selectedItem",Toast.LENGTH_SHORT).show()
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
                val selectedItem = parent?.getItemAtPosition(position).toString()
                Toast.makeText(requireContext(),"Selected item : $selectedItem",Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }


        return root
    }

    private fun loadCalendar() {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(), {
                view, year, monthOfYear , dayOfMonth ->
                tvCalendar.text = "$dayOfMonth/$monthOfYear/$year"
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