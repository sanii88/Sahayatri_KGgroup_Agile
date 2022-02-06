package com.sunny.sahayatribookingsewa.adapter

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.recyclerview.widget.RecyclerView
import com.sunny.sahayatribookingsewa.NotificationChannels
import com.sunny.sahayatribookingsewa.R
import com.sunny.sahayatribookingsewa.model.AdminTicket
import com.sunny.sahayatribookingsewa.model.BookingTicket
import com.sunny.sahayatribookingsewa.repository.BookingRepository
import com.sunny.sahayatribookingsewa.repository.HiringRepository
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
        val btnBook: TextView = view.findViewById(R.id.btnBook)
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

        holder.btnBook.setOnClickListener {

            val builder = AlertDialog.Builder(context)
            builder.setTitle("Book ticket")
            builder.setMessage("Do you want to book this ticket??")
            builder.setIcon(android.R.drawable.ic_dialog_alert)
            builder.setPositiveButton("Yes") { _, _ ->
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val ticketRepo = BookingRepository()
                        val response = ticketRepo.insertTickets(tickets)
                        if (response.success == true) {
                            withContext(Dispatchers.Main) {
                                Toast.makeText(
                                    context,
                                    "Booking successful",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            }
                        }

                    } catch (ex: Exception) {
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
        return lstTickets.size
    }
}