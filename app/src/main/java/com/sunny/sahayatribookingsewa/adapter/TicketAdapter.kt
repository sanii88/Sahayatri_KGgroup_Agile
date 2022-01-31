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
import com.sunny.sahayatribookingsewa.model.BookingTicket
import com.sunny.sahayatribookingsewa.model.Hiring
import com.sunny.sahayatribookingsewa.repository.BookingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

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

        holder.btnDelete.setOnClickListener{

            val builder = AlertDialog.Builder(context)
            builder.setTitle("Delete booking")
            builder.setMessage("Are you sure you want to delete this ??")
            builder.setIcon(android.R.drawable.ic_dialog_alert)
            builder.setPositiveButton("Yes") { _, _ ->
                CoroutineScope(Dispatchers.IO).launch{
                    try{
                        val ticketRepo = BookingRepository()
                        val response = ticketRepo.deleteBooking(tickets._id!!)
                        if (response.success == true) {
                            withContext(Dispatchers.Main) {
                                Toast.makeText(
                                    context,
                                    "Booking Deleted",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            }
                        }
                        withContext(Dispatchers.Main) {
                            lstTicket.remove(tickets)
                            notifyDataSetChanged()
                        }
                    }catch(ex: Exception){
                        withContext(Dispatchers.Main) {
                            Toast.makeText(context, ex.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
            builder.setNegativeButton("No") { _, _ ->
                Toast.makeText(context, "Action cancelled", Toast.LENGTH_SHORT).show()
            }
            val alertDialog: AlertDialog = builder.create()
            alertDialog.setCancelable(false)
            alertDialog.show()

        }
    }

    override fun getItemCount(): Int {
        return lstTicket.size
    }
}