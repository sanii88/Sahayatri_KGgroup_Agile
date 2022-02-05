package com.sunny.sahayatribookingsewa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class AdminDashboard : AppCompatActivity() {
    
    private lateinit var btnTicket : ImageButton
    private lateinit var btnBook : ImageButton
    private lateinit var btnHire : ImageButton
    private lateinit var btnCustomer : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_dashboard)
        supportActionBar?.hide()


        btnTicket = findViewById(R.id.btnTicket)
        btnBook = findViewById(R.id.btnBook)
        btnHire = findViewById(R.id.btnHire)
        btnCustomer = findViewById(R.id.btnCustomer)

        btnTicket.setOnClickListener {
            startActivity(Intent(this, InsertTicket::class.java))
        }

        btnBook.setOnClickListener {
            startActivity(Intent(this, ViewTicket::class.java))
        }

        btnHire.setOnClickListener {
            startActivity(Intent(this, ViewHire::class.java))
        }

        btnCustomer.setOnClickListener {
            startActivity(Intent(this, ShowUser::class.java))
        }
    }
}

