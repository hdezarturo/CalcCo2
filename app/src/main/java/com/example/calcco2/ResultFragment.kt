package com.example.calcco2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import java.text.DecimalFormat


class ResultFragment : Fragment() {
    private val args: ResultFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val totalCO2 = view.findViewById<android.widget.TextView>(R.id.totalCO2)
        val desgloseText = view.findViewById<android.widget.TextView>(R.id.desgloseText)
        val recomendacionesText = view.findViewById<android.widget.TextView>(R.id.recomendacionesText)
        val btnBackHome = view.findViewById<android.widget.Button>(R.id.btnBackHome)

        val factorElectricidad = 0.444f
        val factorAuto = 0.185f
        val factorResiduo = 0.4f
        val factorPapel = 1.2f

        val electricidad = args.electricidad
        val autoKm = args.autoKm
        val residuo = args.residuo
        val papel = args.papel

        val co2Electricidad = electricidad * factorElectricidad / 1000f
        val co2Auto = autoKm * factorAuto / 1000f
        val co2Residuo = residuo * factorResiduo / 1000f
        val co2Papel = papel * factorPapel / 1000f
        val total = co2Electricidad + co2Auto + co2Residuo

        val df = DecimalFormat("#.##")
        totalCO2.text = "${df.format(total)} ton CO₂e"

        val resumen = """
            Electricidad: ${df.format(co2Electricidad)} ton
            Auto: ${df.format(co2Auto)} ton
            Residuos: ${df.format(co2Residuo)} ton
             Papel: ${df.format(co2Papel)} ton
        """.trimIndent()


        desgloseText.text = resumen

        val recomendaciones = when {
            co2Electricidad >= co2Auto && co2Electricidad >= co2Residuo && co2Electricidad >= co2Papel ->
                "* Cambiar a fuentes de energía renovable.\n* Mejorar eficiencia energética.\n* Implementar iluminación LED y equipos eficientes."

            co2Auto >= co2Residuo && co2Auto >= co2Papel ->
                "* Optar por vehículos eléctricos o híbridos.\n* Fomentar el teletrabajo.\n* Optimizar rutas logísticas."

            co2Residuo >= co2Papel ->
                "* Implementar un programa de separación de residuos.\n* Reducir empaques y plásticos.\n* Compostar residuos orgánicos.\n* Explorar valorización energética (waste-to-energy)."

            else ->
                "* Digitalizar documentos para reducir el uso de papel.\n* Usar papel reciclado (reduce emisiones ~70%).\n* Implementar políticas de impresión a doble cara y reutilización."
        }
        recomendacionesText.text = recomendaciones

        btnBackHome.setOnClickListener {
            view.findNavController().navigate(R.id.action_result_to_home)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // No es necesario guardar nada aquí porque los argumentos ya están disponibles
    }


}