package com.sunny.sahayatribookingsewa

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sunny.sahayatribookingsewa.adapter.ActiveTicketAdapter
import com.sunny.sahayatribookingsewa.model.BookingTicket
import com.sunny.sahayatribookingsewa.repository.BookingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

class ActiveTicketActivity : AppCompatActivity() {

    private lateinit var recyclerViewTicket: RecyclerView
    private var activeTicket = mutableListOf<BookingTicket>().asReversed()
    private var toRemove = mutableListOf<BookingTicket>().asReversed()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_active_ticket)

        recyclerViewTicket = findViewById(R.id.recyclerViewActiveTicket)

        CoroutineScope(Dispatchers.IO).launch {
            try {

                val ticketRepo = BookingRepository()
                val response = ticketRepo.getAllBookings()
                if (response.success == true) {

                    activeTicket = response.data!!

                    for (i in activeTicket) {
                        val currentDateTime = stringToDate(getCurrentDateTime())
                        val createdDateTime =
                            stringToDate(getCreatedDateTime(i.createdAt.toString()))

                        var diff: Long = currentDateTime.time - createdDateTime.time
                        val secondsInMilli: Long = 1000
                        val minutesInMilli = secondsInMilli * 60
                        val hoursInMilli = minutesInMilli * 60
                        val daysInMilli = hoursInMilli * 24

                        val elapsedDays: Long = diff / daysInMilli
                        diff %= daysInMilli
                        val elapsedHours: Long = diff / hoursInMilli
                        diff %= hoursInMilli
                        val elapsedMinutes: Long = diff / minutesInMilli
                        diff %= minutesInMilli
                        val elapsedSeconds: Long = diff / secondsInMilli

                        if (elapsedDays > 0 || (elapsedHours.equals(6) && elapsedMinutes >= 15)) {
                            toRemove.add(i)
                        }
                    }

                    activeTicket.removeAll(toRemove)

                    withContext(Dispatchers.Main) {
                        val adapter = ActiveTicketAdapter(this@ActiveTicketActivity, activeTicket)
                        recyclerViewTicket.adapter = adapter
                        recyclerViewTicket.layoutManager =
                            LinearLayoutManager(
                                this@ActiveTicketActivity,
                                LinearLayoutManager.VERTICAL,
                                false
                            )
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@ActiveTicketActivity, "hello", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@ActiveTicketActivity, ex.toString(), Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun stringToDate(date: String): Date {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return dateFormat.parse(date)
    }

    @SuppressLint("SimpleDateFormat")
    private fun getCurrentDateTime(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return sdf.format(Date())
    }

    @SuppressLint("SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.O)
    private fun getCreatedDateTime(timeStamp: String): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        val createdAt = LocalDateTime.parse(timeStamp, formatter)
        val out = Date.from(createdAt.atZone(ZoneId.systemDefault()).toInstant())
        return sdf.format(out)
    }
}