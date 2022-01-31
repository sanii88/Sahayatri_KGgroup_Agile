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
import com.sunny.sahayatribookingsewa.model.User
import com.sunny.sahayatribookingsewa.repository.HiringRepository
import com.sunny.sahayatribookingsewa.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class UserAdapter(
    private val context: Context,
    private val lstUser: MutableList<User>

) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvUsername: TextView = view.findViewById(R.id.tvUsername)
        val tvPhone: TextView = view.findViewById(R.id.tvPhone)
        val btnDelete: ImageButton = view.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_list, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserAdapter.UserViewHolder, position: Int) {
        val user = lstUser[position]
        holder.tvUsername.text = user.username
        holder.tvPhone.text = user.phone

        holder.btnDelete.setOnClickListener {

            val builder = AlertDialog.Builder(context)
            builder.setTitle("Delete user")
            builder.setMessage("Are you sure you want to delete this ??")
            builder.setIcon(android.R.drawable.ic_dialog_alert)
            builder.setPositiveButton("Yes") { _, _ ->
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val userRepo = UserRepository()
                        val response = userRepo.deleteUsers(user._id!!)
                        if (response.success == true) {
                            withContext(Dispatchers.Main) {
                                Toast.makeText(
                                    context,
                                    "User Deleted",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            }
                        }
                        withContext(Dispatchers.Main) {
                            lstUser.remove(user)
                            notifyDataSetChanged()
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
        return lstUser.size
    }
}