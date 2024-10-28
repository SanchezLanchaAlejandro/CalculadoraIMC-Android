package com.example.calculadoraimc

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider

class MainActivity : AppCompatActivity() {
    private lateinit var cardHombre: CardView
    private lateinit var cardMujer: CardView
    private lateinit var alturaTextView: TextView
    private lateinit var edadTextView: TextView
    private lateinit var pesoTextView: TextView
    private lateinit var slider: RangeSlider
    private lateinit var botonMenosPeso: FloatingActionButton
    private lateinit var botonMasPeso: FloatingActionButton
    private lateinit var botonMasEdad: FloatingActionButton
    private lateinit var botonMenosEdad: FloatingActionButton
    private lateinit var botonCalcular: Button

    private var altura: Int = 180
    private var peso: Int = 60
    private var edad: Int = 30

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        cardHombre = findViewById(R.id.cardView2)
        cardMujer = findViewById(R.id.cardViewMujer)
        alturaTextView = findViewById(R.id.textViewCm)
        edadTextView = findViewById(R.id.textView2)
        pesoTextView = findViewById(R.id.textViewNumPeso)
        slider = findViewById(R.id.rangeSlider)
        botonMenosPeso = findViewById(R.id.floatingActionButton5)
        botonMasPeso = findViewById(R.id.floatingActionButton)
        botonMasEdad = findViewById(R.id.floatingActionButton3)
        botonMenosEdad = findViewById(R.id.floatingActionButton2)
        botonCalcular = findViewById(R.id.button)

        actualizarPeso()
        actualizarEdad()
        cardHombre.setCardBackgroundColor(getColor(R.color.card_background))
        cardMujer.setCardBackgroundColor(getColor(R.color.card_background))

        slider.addOnChangeListener { _, value, _ ->
            altura = value.toInt()
            alturaTextView.text = "$altura cm"
        }

        botonMenosPeso.setOnClickListener {
            peso-=1
            actualizarPeso()
        }

        botonMasPeso.setOnClickListener {
            peso+=1
            actualizarPeso()
        }
        
        botonMasEdad.setOnClickListener {
            edad+=1
            actualizarEdad()
        }

        botonMenosEdad.setOnClickListener {
            edad-=1
            actualizarEdad()
        }

        botonCalcular.setOnClickListener {
            val imc = calcularIMC()
            val intent = Intent(this, ResultIMCActivity::class.java)
            intent.putExtra("imc", imc)
            startActivity(intent)
        }

        cardHombre.setOnClickListener {
            cardHombre.setCardBackgroundColor(getColor(R.color.cardPulsado))
            cardMujer.setCardBackgroundColor(getColor(R.color.card_background))
        }

        cardMujer.setOnClickListener {
            cardMujer.setCardBackgroundColor(getColor(R.color.cardPulsado))
            cardHombre.setCardBackgroundColor(getColor(R.color.card_background))
        }

    }
    
    private fun actualizarPeso() {
        pesoTextView.text = "$peso kg"
    }
    
    private fun actualizarEdad(){
        edadTextView.text = "$edad"
    }

    private fun calcularIMC(): Double {
        val alturaMetros = altura / 100.0
        return peso / (alturaMetros * alturaMetros)
    }
}