package com.example.calculadoraimc

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultIMCActivity : AppCompatActivity() {
    private lateinit var textViewResult: TextView
    private lateinit var textViewNomPeso: TextView
    private lateinit var textViewTextPeso: TextView
    private lateinit var botonReCalcular: Button

    @SuppressLint("DefaultLocale")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result_imcactivity)

        textViewResult = findViewById(R.id.textView4)
        textViewNomPeso = findViewById(R.id.textView3)
        textViewTextPeso = findViewById(R.id.textView5)
        botonReCalcular = findViewById(R.id.button2)

        val imc = intent.getDoubleExtra("imc", 0.0)
        val imcText = String.format("%.2f", imc)

        val tipoPeso = when{
            imc < 18.5 -> getString(R.string.pesoBajo)
            imc < 24.9 -> getString(R.string.pesoNormal)
            imc < 29.9 -> getString(R.string.pesoSobrepeso)
            imc >= 30.0 -> getString(R.string.pesoObesidad)
            else -> "Error"
        }

        val texto = when{
            imc < 18.5 -> getString(R.string.textoPesoBajo)
            imc < 24.9 -> getString(R.string.textoPesoNormal)
            imc < 29.9 -> getString(R.string.textoPesoSobrepeso)
            imc >= 30.0 -> getString(R.string.textoPesoObesidad)
            else -> "Error"
        }

        when {
            imc < 18.5 -> textViewNomPeso.setTextColor(getColor(R.color.yellow))
            imc < 24.9 -> textViewNomPeso.setTextColor(getColor(R.color.green))
            imc < 29.9 -> textViewNomPeso.setTextColor(getColor(R.color.orange))
            imc >= 30.0 -> textViewNomPeso.setTextColor(getColor(R.color.red))
        }

        textViewResult.text = imcText
        textViewNomPeso.text = tipoPeso
        textViewTextPeso.text = texto

        botonReCalcular.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

    }
}