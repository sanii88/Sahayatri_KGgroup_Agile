package com.sunny.sahayatribookingsewa.adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.sunny.sahayatribookingsewa.R
import com.sunny.sahayatribookingsewa.model.AdminTicket
import com.sunny.sahayatribookingsewa.model.BookingTicket
import com.sunny.sahayatribookingsewa.repository.BookingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class AdminAdapter(

    private val context: Context,
    private val lstTickets: MutableList<AdminTicket>

) : RecyclerView.Adapter<AdminAdapter.AdminViewHolder>() {

    class AdminViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvVehicleType: TextView = view.findViewById(R.id.tvVehicleType)
        val tvTravelDate: TextView = view.findViewById(R.id.tvTravelDate)
        val tvSeatNo: TextView = view.findViewById(R.id.tvSeatNo)
        val tvBoardingPoint: TextView = view.findViewById(R.id.tvBoardingPoint)
        val tvRoute: TextView = view.findViewById(R.id.tvRoute)
        val tvPrice: TextView = view.findViewById(R.id.tvPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdminViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.insert_ticket_list, parent, false)
        return AdminViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdminViewHolder, position: Int) {
        val tickets = lstTickets[position]
        holder.tvVehicleType.text = tickets.vehicle_type
        holder.tvTravelDate.text = tickets.departure_date
        holder.tvSeatNo.text = tickets.seatNo
        holder.tvBoardingPoint.text = tickets.boarding_point
        holder.tvRoute.text = tickets.route
        holder.tvPrice.text = tickets.price

    }

    override fun getItemCount(): Int {
        return lstTickets.size
    }
}