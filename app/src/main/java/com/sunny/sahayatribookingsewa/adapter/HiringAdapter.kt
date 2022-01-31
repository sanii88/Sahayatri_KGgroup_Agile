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
import com.sunny.sahayatribookingsewa.model.Hiring
import com.sunny.sahayatribookingsewa.repository.BookingRepository
import com.sunny.sahayatribookingsewa.repository.HiringRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class HiringAdapter(
    private val context: Context,
    private val lstHiring: MutableList<Hiring>

) : RecyclerView.Adapter<HiringAdapter.HiringViewHolder>() {

    class HiringViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvVehicleType: TextView = view.findViewById(R.id.tvVehicleType)
        val tvHiringDate: TextView = view.findViewById(R.id.tvHiringDate)
        val tvHiringDays: TextView = view.findViewById(R.id.tvHiringDays)
        val btnDelete: ImageButton = view.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HiringViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.hiring_list, parent, false)
        return HiringViewHolder(view)
    }

    override fun onBindViewHolder(holder: HiringViewHolder, position: Int) {
        val hiring = lstHiring[position]
        holder.tvVehicleType.text = hiring.vehicle_type
        holder.tvHiringDate.text = hiring.departure_date
        holder.tvHiringDays.text = hiring.hireDays

        holder.btnDelete.setOnClickListener{

            val builder = AlertDialog.Builder(context)
            builder.setTitle("Delete hiring")
            builder.setMessage("Are you sure you want to delete this ??")
            builder.setIcon(android.R.drawable.ic_dialog_alert)
            builder.setPositiveButton("Yes") { _, _ ->
                CoroutineScope(Dispatchers.IO).launch{
                    try{
                        val hiringRepo = HiringRepository()
                        val response = hiringRepo.deleteHiring(hiring._id!!)
                        if (response.success == true) {
                            withContext(Dispatchers.Main) {
                                Toast.makeText(
                                    context,
                                    "Hiring Deleted",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            }
                        }
                        withContext(Dispatchers.Main) {
                            lstHiring.remove(hiring)
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
    return lstHiring.size
}
}