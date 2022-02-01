package com.sunny.sahayatribookingsewa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sunny.sahayatribookingsewa.adapter.TicketAdapter
import com.sunny.sahayatribookingsewa.model.BookingTicket
import com.sunny.sahayatribookingsewa.repository.BookingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class ViewTicket : AppCompatActivity() {

    private lateinit var recyclerViewTicket: RecyclerView
    private var lstTicket = mutableListOf<BookingTicket>().asReversed()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_ticket2)
        supportActionBar?.hide()


        recyclerViewTicket = findViewById(R.id.recyclerViewTicket)

        CoroutineScope(Dispatchers.IO).launch {
            try {

                val ticketRepo = BookingRepository()
                val response = ticketRepo.getAllBookings()
                if (response.success == true) {
                    lstTicket = response.data!!
                    withContext(Dispatchers.Main) {
                        val adapter = TicketAdapter(this@ViewTicket, lstTicket)
                        recyclerViewTicket.adapter = adapter
                        recyclerViewTicket.layoutManager =
                            LinearLayoutManager(
                                this@ViewTicket,
                                LinearLayoutManager.VERTICAL,
                                false
                            )
                    }
                }
            } catch (ex: Exception) {

            }

        }
    }
}