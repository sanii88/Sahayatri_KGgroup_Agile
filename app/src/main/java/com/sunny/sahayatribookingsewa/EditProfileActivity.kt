package com.sunny.sahayatribookingsewa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.sunny.sahayatribookingsewa.api.ServiceBuilder
import com.sunny.sahayatribookingsewa.model.User
import com.sunny.sahayatribookingsewa.repository.UserRepository
import com.sunny.sahayatribookingsewa.ui.notifications.NotificationsFragment
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

        getUserData()

        btnUpdateProfile.setOnClickListener {
            updateUser()
        }
    }

    private fun updateUser() {
        val username = etName.text.toString()
        val phone = etContact.text.toString()
        val email = etEmail.text.toString()
        val address = etAddress.text.toString()
        val id : String? = null

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val user = User(
                    username = username, phone = phone, email = email, address = address
                )
                val userRepo = UserRepository()
                val response = userRepo.updateUser(id!! , user)
                if (response.success == true) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@EditProfileActivity,
                            "Profile Updated!!",
                            Toast.LENGTH_SHORT
                        ).show()

                        val intent =
                            Intent(this@EditProfileActivity, NotificationsFragment::class.java)
                        startActivity(intent)
                        finish()
                    }
                }

            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@EditProfileActivity,
                        ex.toString(), Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun getUserData() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val userRepo = UserRepository()
                val response = userRepo.getMe()
                if (response.success == true) {
                    val user = response.user!!
                    withContext(Dispatchers.Main) {
                        etName.setText("${user.username}")
                        etContact.setText("${user.phone}")
                        etEmail.setText("${user.email}")
                        etAddress.setText("${user.address}")
                    }
                }
            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@EditProfileActivity, "Error:${ex}", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

}