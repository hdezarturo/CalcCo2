package com.example.calcco2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController


class FormFragment : Fragment() {
    private var electricidadInput: String = ""
    private var autoInput: String = ""
    private var residuoInput: String = ""
    private var papelInput: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_form, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editElectricidad =
            view.findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.editElectricidad)
        val editAuto =
            view.findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.editAuto)
        val editResiduo =
            view.findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.editResiduo)
        val editPapel =
            view.findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.editPapel)
        val btnCalcular = view.findViewById<android.widget.Button>(R.id.btnCalcular)

        // Restaurar estado si existe
        if (savedInstanceState != null) {
            electricidadInput = savedInstanceState.getString("electricidad", "")
            autoInput = savedInstanceState.getString("auto", "")
            residuoInput = savedInstanceState.getString("residuo", "")
            papelInput = savedInstanceState.getString("papel", "")
            editElectricidad.setText(electricidadInput)
            editAuto.setText(autoInput)
            editResiduo.setText(residuoInput)
            editPapel.setText(papelInput)
        }

        btnCalcular.setOnClickListener {
            val electricidadStr = editElectricidad.text.toString()
            val autoStr = editAuto.text.toString()
            val residuoStr = editResiduo.text.toString()
            val papelStr = editPapel.text.toString()

            if (electricidadStr.isBlank() || autoStr.isBlank() || residuoStr.isBlank() || papelStr.isBlank()) {
                return@setOnClickListener
            }

            val electricidad = electricidadStr.toFloatOrNull() ?: 0f
            val autoKm = autoStr.toFloatOrNull() ?: 0f
            val residuo = residuoStr.toFloatOrNull() ?: 0f
            val papel = papelStr.toFloatOrNull() ?: 0f

            val action =
                FormFragmentDirections.actionFormToResult(electricidad, autoKm, residuo, papel)
            view.findNavController().navigate(action)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val view = view ?: return
        outState.putString(
            "electricidad",
            view.findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.editElectricidad).text.toString()
        )
        outState.putString(
            "auto",
            view.findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.editAuto).text.toString()
        )
        outState.putString(
            "residuo",
            view.findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.editResiduo).text.toString()
        )
        outState.putString(
            "papel",
            view.findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.editPapel).text.toString()
        )
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }
}