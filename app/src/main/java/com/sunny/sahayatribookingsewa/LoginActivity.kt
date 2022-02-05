package com.sunny.sahayatribookingsewa

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.app.ActivityCompat
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.sunny.sahayatribookingsewa.api.ServiceBuilder
import com.sunny.sahayatribookingsewa.model.User
import com.sunny.sahayatribookingsewa.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class LoginActivity : AppCompatActivity() {

    private lateinit var etPhone: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var btnLogin: Button
    private lateinit var tvRegister: TextView
    private lateinit var chkRememberMe: CheckBox
    private lateinit var linearLayout: LinearLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()


        etPhone = findViewById(R.id.etPhone)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        tvRegister = findViewById(R.id.tvRegister)
        linearLayout = findViewById(R.id.linearLayout)

        //register
        tvRegister.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }


        btnLogin.setOnClickListener {

            //validation
            if (TextUtils.isEmpty(etPhone.text)) {
                etPhone.error = "Phone number must not be empty!!"
                etPhone.requestFocus()
                return@setOnClickListener
            } else if (TextUtils.isEmpty(etPassword.text)) {
                etPassword.error = "Password must not be empty!!"
                etPassword.requestFocus()
                return@setOnClickListener
            }

            login()
        }

        //check permission
        if (!permissionCheck()) {
            permissionAsk()
        }

    }


    private val permissions = arrayOf(
        android.Manifest.permission.CAMERA,
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
        android.Manifest.permission.ACCESS_FINE_LOCATION
    )

    private fun permissionCheck(): Boolean {
        var hasPermission = true
        for (permission in permissions) {
            if (ActivityCompat.checkSelfPermission(this, permission) !=
                PackageManager.PERMISSION_GRANTED
            ) {
                hasPermission = false
                break
            }
        }
        return hasPermission
    }

    private fun permissionAsk() {
        ActivityCompat.requestPermissions(
            this@LoginActivity,
            permissions, 1
        )
    }

    private fun login() {

        val phone = etPhone.text.toString()
        val password = etPassword.text.toString()

        //API
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val repository = UserRepository()
                val response = repository.loginUser(phone, password)
                if (response.success == true) {
                    // Save token
                    ServiceBuilder.token = "Bearer ${response.token}"
                    if (response.userType == "Customer") {
                        startActivity(
                            Intent(
                                this@LoginActivity,
                                MainDashboard::class.java
                            )
                        )
                        finish()
                    }
                    else{
                        startActivity(
                            Intent(
                                this@LoginActivity,
                                AdminDashboard::class.java
                            )
                        )
                        finish()
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        val snack =
                            Snackbar.make(
                                linearLayout,
                                "Invalid credentials",
                                Snackbar.LENGTH_LONG
                            )
                        snack.setAction("OK", View.OnClickListener {
                            snack.dismiss()
                        })
                        snack.show()
                    }
                }
            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Log.d("Error", ex.toString())

                    Toast.makeText(
                        this@LoginActivity,
                        ex.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}