package com.sunny.sahayatribookingsewa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sunny.sahayatribookingsewa.adapter.HiringAdapter
import com.sunny.sahayatribookingsewa.adapter.UserAdapter
import com.sunny.sahayatribookingsewa.model.Hiring
import com.sunny.sahayatribookingsewa.model.User
import com.sunny.sahayatribookingsewa.repository.HiringRepository
import com.sunny.sahayatribookingsewa.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class ShowUser : AppCompatActivity() {

    private lateinit var recyclerViewUser: RecyclerView
    private var lstUser = mutableListOf<User>().asReversed()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_user)
        supportActionBar?.hide()


        recyclerViewUser = findViewById(R.id.recyclerViewUser)

        CoroutineScope(Dispatchers.IO).launch {
            try {

                val userRepo = UserRepository()
                val response = userRepo.getAllUsers()
                if (response.success == true) {
                    lstUser = response.data!!
                    withContext(Dispatchers.Main) {
                        val adapter = UserAdapter(this@ShowUser, lstUser)
                        recyclerViewUser.adapter = adapter
                        recyclerViewUser.layoutManager =
                            LinearLayoutManager(
                                this@ShowUser,
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