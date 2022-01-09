package com.sunny.sahayatribookingsewa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.content.ContentProviderCompat.requireContext

class BoardingActivity : AppCompatActivity() {

    private lateinit var tvBoarding : TextView
    private lateinit var spBoarding : Spinner
    private lateinit var etName : EditText
    private lateinit var etContact : EditText
    private lateinit var btnBoarding : Button

    private val boardingPoint =
        arrayOf("Koteshwor", "Buspark", "Kalanki")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_boarding)

        tvBoarding = findViewById(R.id.tvBoarding)
        spBoarding = findViewById(R.id.spBoarding)
        etName = findViewById(R.id.etName)
        etContact = findViewById(R.id.etContact)
        btnBoarding = findViewById(R.id.btnBoarding)

        btnBoarding.setOnClickListener{
            val intent = Intent(this, TicketActivity::class.java)
            startActivity(intent)
        }

        //Array Adapter
        val boardingAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, boardingPoint)

        //Setting the adapter to spinner's adapter
        spBoarding.adapter = boardingAdapter

        //On item selected listener on spinner
        //for boarding point
        spBoarding.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                Toast.makeText(this@BoardingActivity,"Selected item : $selectedItem",Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
    }
}