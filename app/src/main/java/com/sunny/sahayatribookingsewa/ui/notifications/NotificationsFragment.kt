package com.sunny.sahayatribookingsewa.ui.notifications

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sunny.sahayatribookingsewa.*
import com.sunny.sahayatribookingsewa.databinding.FragmentNotificationsBinding
import com.sunny.sahayatribookingsewa.repository.UserRepository
import com.sunny.sahayatribookingsewa.util.SavedData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel
    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!

    private lateinit var tvName : TextView
    private lateinit var tvContact : TextView
    private lateinit var btnEditProfile : Button
    private lateinit var btnTicket : Button
    private lateinit var btnHire : Button
    private lateinit var btnActiveTicket : Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        tvName = root.findViewById(R.id.tvName)
        tvContact = root.findViewById(R.id.tvContact)
        btnEditProfile = root.findViewById(R.id.btnEditProfile)
        btnTicket = root.findViewById(R.id.btnTicket)
        btnHire = root.findViewById(R.id.btnHire)
        btnActiveTicket = root.findViewById(R.id.btnActiveTicket)


        CoroutineScope(Dispatchers.IO).launch {
            try {
                val userRepo = UserRepository()
                val response = userRepo.getMe()

                if (response.success == true) {
                    tvName.text = response.user!!.username
                    tvContact.text = response.user.phone
                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(context, response.message, Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, ex.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }

        btnEditProfile.setOnClickListener {
            val intent = Intent(view?.context, EditProfileActivity::class.java)
            startActivity(intent)
        }

        btnTicket.setOnClickListener {
            val intent = Intent(view?.context, ViewTicketActivity::class.java)
            startActivity(intent)
        }

        btnHire.setOnClickListener {
            val intent = Intent(view?.context, ViewHiringActivity::class.java)
            startActivity(intent)
        }

        btnActiveTicket.setOnClickListener {
            val intent = Intent(view?.context, ActiveTicketActivity::class.java)
            startActivity(intent)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}