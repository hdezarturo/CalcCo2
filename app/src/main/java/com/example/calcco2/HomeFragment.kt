package com.example.calcco2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController



class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<android.widget.Button>(R.id.btnGoToForm).setOnClickListener {
            view.findNavController().navigate(R.id.action_home_to_form)
        }

        view.findViewById<android.widget.Button>(R.id.btnGoToInfo).setOnClickListener {
            view.findNavController().navigate(R.id.action_home_to_info)
        }
    }
}