package com.sunny.sahayatribookingsewa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.sunny.sahayatribookingsewa.model.User
import com.sunny.sahayatribookingsewa.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class EditProfileActivity : AppCompatActivity() {

    private lateinit var tvImage: ImageView
    private lateinit var etName: EditText
    private lateinit var etContact: EditText
    private lateinit var etEmail: EditText
    private lateinit var etAddress: EditText
    private lateinit var btnUpdateProfile: Button
    private var id: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        supportActionBar?.hide()


        tvImage = findViewById(R.id.tvImage)
        etName = findViewById(R.id.etName)
        etContact = findViewById(R.id.etContact)
        etEmail = findViewById(R.id.etEmail)
        etAddress = findViewById(R.id.etAddress)
        btnUpdateProfile = findViewById(R.id.btnUpdateProfile)


        btnUpdateProfile.setOnClickListener {

        }
    }

}