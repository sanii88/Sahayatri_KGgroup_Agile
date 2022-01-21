package com.sunny.sahayatribookingsewa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.sunny.sahayatribookingsewa.model.User
import com.sunny.sahayatribookingsewa.repository.UserRepository
import com.sunny.sahayatribookingsewa.util.SavedData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class RegisterActivity : AppCompatActivity() {

    private lateinit var etUsername: EditText
    private lateinit var etPhone: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConfirmPassword: EditText
    private lateinit var btnAddUser: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        etUsername = findViewById(R.id.etUsername)
        etPhone = findViewById(R.id.etPhone)
        etPassword = findViewById(R.id.etPassword)
        etConfirmPassword = findViewById(R.id.etConfirmPassword)
        btnAddUser = findViewById(R.id.btnAddUser)

        btnAddUser.setOnClickListener {

            val username = etUsername.text.toString()
            val phone = etPhone.text.toString()
            val password = etPassword.text.toString()
            val confirmPassword = etConfirmPassword.text.toString()

            if (password != confirmPassword) {
                etPassword.error = "Password does not match"
                etPassword.requestFocus()
                return@setOnClickListener
            } else {
                val user =
                    User(username = username, phone = phone, password = password)
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val repository = UserRepository()
                        val response = repository.registerUser(user)
                        if (response.success == true) {
                            withContext(Dispatchers.Main) {
                                Toast.makeText(
                                    this@RegisterActivity,
                                    "Successfully registered",
                                    Toast.LENGTH_SHORT
                                ).show()
                                startActivity(
                                    Intent(
                                        this@RegisterActivity,
                                        LoginActivity::class.java
                                    )
                                )
                            }
                        }
                    } catch (ex: Exception) {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(this@RegisterActivity, ex.toString(), Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }

            }

        }
    }

}