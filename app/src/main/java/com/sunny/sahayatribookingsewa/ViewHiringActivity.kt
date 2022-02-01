package com.sunny.sahayatribookingsewa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sunny.sahayatribookingsewa.adapter.HiringAdapter
import com.sunny.sahayatribookingsewa.model.Hiring
import com.sunny.sahayatribookingsewa.repository.HiringRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class ViewHiringActivity : AppCompatActivity() {

    private lateinit var recyclerViewHiring: RecyclerView
    private var lstHiring = mutableListOf<Hiring>().asReversed()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_hiring)
        supportActionBar?.hide()


        recyclerViewHiring = findViewById(R.id.recyclerViewHiring)

        CoroutineScope(Dispatchers.IO).launch {
            try {

                val hiringRepo = HiringRepository()
                val response = hiringRepo.getAllHiring()
                if (response.success == true) {
                    lstHiring = response.data!!
                    withContext(Dispatchers.Main) {
                        val adapter = HiringAdapter(this@ViewHiringActivity, lstHiring)
                        recyclerViewHiring.adapter = adapter
                        recyclerViewHiring.layoutManager =
                            LinearLayoutManager(
                                this@ViewHiringActivity,
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