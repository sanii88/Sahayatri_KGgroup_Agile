package com.sunny.sahayatribookingsewa.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sunny.sahayatribookingsewa.R
import com.sunny.sahayatribookingsewa.model.BookingTicket
import com.sunny.sahayatribookingsewa.model.Hiring

class TicketAdapter(

    private val context: Context,
    private val lstTicket: MutableList<BookingTicket>

) : RecyclerView.Adapter<TicketAdapter.TicketViewHolder>() {

    class TicketViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvVehicleType: TextView = view.findViewById(R.id.tvVehicleType)
        val tvTravelDate: TextView = view.findViewById(R.id.tvTravelDate)
        val tvSeatNo: TextView = view.findViewById(R.id.tvSeatNo)
        val tvBoardingPoint: TextView = view.findViewById(R.id.tvBoardingPoint)
        val btnDelete: ImageButton = view.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.ticket_list, parent, false)
        return TicketViewHolder(view)
    }

    override fun onBindViewHolder(holder: TicketViewHolder, position: Int) {
        val tickets = lstTicket[position]
        holder.tvVehicleType.text = tickets.vehicle_type
        holder.tvTravelDate.text = tickets.departure_date
        holder.tvSeatNo.text = tickets.seatNo
        holder.tvBoardingPoint.text = tickets.boarding_point
    }

    override fun getItemCount(): Int {
        return lstTicket.size
    }
}