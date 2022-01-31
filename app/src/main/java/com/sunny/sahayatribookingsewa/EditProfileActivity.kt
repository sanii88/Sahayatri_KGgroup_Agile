package com.sunny.sahayatribookingsewa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
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
    private lateinit var etPassword: EditText
    private lateinit var btnUpdateProfile: Button
    private var id: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        tvImage = findViewById(R.id.tvImage)
        etName = findViewById(R.id.etName)
        etContact = findViewById(R.id.etContact)
        etPassword = findViewById(R.id.etPassword)
        btnUpdateProfile = findViewById(R.id.btnUpdateProfile)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val userRepo = UserRepository()
                val response = userRepo.getMe()

                if (response.success == true) {
                    id = response.user!!._id
                    etName.setText(response.user.username)
                    etContact.setText(response.user.phone)
                    etPassword.setText(response.user.password)

                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@EditProfileActivity,
                            response.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@EditProfileActivity, ex.toString(), Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
        btnUpdateProfile.setOnClickListener {
            val username = etName.text.toString()
            val phone = etContact.text.toString()
            val password = etPassword.text.toString()

            val user = User(username = username, phone = phone, password = password)

            updateMe(id!!, user)

        }
    }

    private fun updateMe(id: String, user: User) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val userRepo = UserRepository()
                val response = userRepo.updateUser(id, user)

                if (response.success == true) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@EditProfileActivity, "Updated Successfully!!", Toast.LENGTH_SHORT).show()
                    }

                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@EditProfileActivity, response.message, Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@EditProfileActivity, ex.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}