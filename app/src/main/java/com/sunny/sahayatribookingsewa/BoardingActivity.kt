package com.sunny.sahayatribookingsewa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.sunny.sahayatribookingsewa.util.SavedData

class BoardingActivity : AppCompatActivity() {

    private lateinit var spBoarding: Spinner
    private lateinit var etName: EditText
    private lateinit var etContact: EditText
    private lateinit var btnBoarding: Button

    private val boardingPoint =
        arrayOf("Koteshwor", "Buspark", "Kalanki")

    companion object {
        var boarding_point: String? = null
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_boarding)

        spBoarding = findViewById(R.id.spBoarding)
        etName = findViewById(R.id.etName)
        etContact = findViewById(R.id.etContact)
        btnBoarding = findViewById(R.id.btnBoarding)


//        Toast.makeText(this, SavedData.vehicleType , Toast.LENGTH_SHORT).show()


        btnBoarding.setOnClickListener {
            saveBoarding()
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
                boarding_point = parent?.getItemAtPosition(position).toString()
                Toast.makeText(
                    this@BoardingActivity,
                    "Selected item : $boarding_point",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
    }

    private fun saveBoarding() {

        val contact = etContact.text.toString()
        val name = etName.text.toString()

        SavedData.setData("myName", name)
        SavedData.setData("myContact", contact)
        SavedData.setData("myBoardingPoint", boarding_point.toString())

        val intent = Intent(this, BookingTicketActivity::class.java)
        startActivity(intent)
    }

}