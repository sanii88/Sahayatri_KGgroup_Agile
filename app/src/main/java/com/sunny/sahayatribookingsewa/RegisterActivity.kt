package com.sunny.sahayatribookingsewa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
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
    private lateinit var etEmail: EditText
    private lateinit var etAddress: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConfirmPassword: EditText
    private lateinit var btnAddUser: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        supportActionBar?.hide()


        etUsername = findViewById(R.id.etUsername)
        etPhone = findViewById(R.id.etPhone)
        etEmail = findViewById(R.id.etEmail)
        etAddress = findViewById(R.id.etAddress)
        etPassword = findViewById(R.id.etPassword)
        etConfirmPassword = findViewById(R.id.etConfirmPassword)
        btnAddUser = findViewById(R.id.btnAddUser)

        btnAddUser.setOnClickListener {


            //validation
            if (TextUtils.isEmpty(etUsername.text)) {
                etUsername.error = "Phone number must not be empty!!"
                etUsername.requestFocus()
                return@setOnClickListener
            } else if (TextUtils.isEmpty(etPhone.text)) {
                etPhone.error = "Phone number must not be empty!!"
                etPhone.requestFocus()
                return@setOnClickListener
            } else if (TextUtils.isEmpty(etEmail.text)) {
                etEmail.error = "Email must not be empty!!"
                etEmail.requestFocus()
                return@setOnClickListener
            }else if (TextUtils.isEmpty(etAddress.text)) {
                etAddress.error = "Address must not be empty!!"
                etAddress.requestFocus()
                return@setOnClickListener
            }else if (TextUtils.isEmpty(etPassword.text)) {
                etPassword.error = "Password must not be empty!!"
                etPassword.requestFocus()
                return@setOnClickListener
            }

            val username = etUsername.text.toString()
            val phone = etPhone.text.toString()
            val email = etEmail.text.toString()
            val address = etAddress.text.toString()
            val password = etPassword.text.toString()
            val confirmPassword = etConfirmPassword.text.toString()

            if (password != confirmPassword) {
                etPassword.error = "Password does not match"
                etPassword.requestFocus()
                return@setOnClickListener
            } else {
                val user =
                    User(username = username, phone = phone, email = email , address = address , password = password)
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