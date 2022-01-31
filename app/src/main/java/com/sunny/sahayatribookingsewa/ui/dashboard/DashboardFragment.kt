package com.sunny.sahayatribookingsewa.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sunny.sahayatribookingsewa.R
import com.sunny.sahayatribookingsewa.adapter.AdminAdapter
import com.sunny.sahayatribookingsewa.adapter.TicketAdapter
import com.sunny.sahayatribookingsewa.databinding.FragmentDashboardBinding
import com.sunny.sahayatribookingsewa.model.AdminTicket
import com.sunny.sahayatribookingsewa.model.BookingTicket
import com.sunny.sahayatribookingsewa.repository.BookingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerViewTicket: RecyclerView
    private var lstTickets = mutableListOf<AdminTicket>().asReversed()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        recyclerViewTicket = root.findViewById(R.id.recyclerViewTicket)

        CoroutineScope(Dispatchers.IO).launch {
            try {

                val ticketRepo = BookingRepository()
                val response = ticketRepo.getTickets()
                if (response.success == true) {
                    lstTickets = response.data!!
                    withContext(Dispatchers.Main) {
                        val adapter = AdminAdapter(requireContext(), lstTickets)
                        recyclerViewTicket.adapter = adapter
                        recyclerViewTicket.layoutManager =
                            LinearLayoutManager(
                                requireContext(),
                                LinearLayoutManager.VERTICAL,
                                false
                            )
                    }
                }
            } catch (ex: Exception) {

            }

        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}