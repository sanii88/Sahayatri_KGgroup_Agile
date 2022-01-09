package com.sunny.sahayatribookingsewa.ui.boarding

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import com.sunny.sahayatribookingsewa.R
import com.sunny.sahayatribookingsewa.TicketActivity

class BoardingFragment : Fragment() {

    private lateinit var viewModel: BoardingViewModel

    private lateinit var tvBoarding : TextView
    private lateinit var spBoarding : Spinner
    private lateinit var etFullname : EditText
    private lateinit var etContact : EditText
    private lateinit var btnBoarding : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.boarding_fragment, container, false)

        tvBoarding = view.findViewById(R.id.tvBoarding)
        spBoarding = view.findViewById(R.id.spBoarding)
        etFullname = view.findViewById(R.id.etFullname)
        etContact = view.findViewById(R.id.etContact)
        btnBoarding = view.findViewById(R.id.btnBoarding)

        btnBoarding.setOnClickListener{
            val intent = Intent(view?.context, TicketActivity::class.java)
            startActivity(intent)
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BoardingViewModel::class.java)
        // TODO: Use the ViewModel

    }

}