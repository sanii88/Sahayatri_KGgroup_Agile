package com.sunny.sahayatribookingsewa.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sunny.sahayatribookingsewa.R
import com.sunny.sahayatribookingsewa.model.Hiring

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

    }

override fun getItemCount(): Int {
    return lstHiring.size
}
}