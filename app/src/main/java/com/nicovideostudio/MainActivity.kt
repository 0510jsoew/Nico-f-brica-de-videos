
package com.nicovideostudio

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.widget.*
import android.text.InputType

class MainActivity : Activity() {

    private lateinit var titulo: EditText
    private lateinit var duracion: Spinner
    private lateinit var resultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = Color.rgb(15, 18, 35)
        window.navigationBarColor = Color.rgb(15, 18, 35)

        val fondo = LinearLayout(this)
        fondo.orientation = LinearLayout.VERTICAL
        fondo.setPadding(28, 30, 28, 24)
        fondo.setBackgroundColor(Color.rgb(15, 18, 35))

        val encabezado = TextView(this)
        encabezado.text = "🎬 NICO VIDEO STUDIO"
        encabezado.textSize = 25f
        encabezado.setTextColor(Color.WHITE)
        encabezado.gravity = Gravity.CENTER
        encabezado.setPadding(0, 10, 0, 12)
        fondo.addView(encabezado)

        val subtitulo = TextView(this)
        subtitulo.text = "Tu fábrica de videos animados"
        subtitulo.textSize = 16f
        subtitulo.gravity = Gravity.CENTER
        subtitulo.setTextColor(Color.LTGRAY)
        fondo.addView(subtitulo)

        val etiqueta = TextView(this)
        etiqueta.text = "\n¿De qué tratará tu video?"
        etiqueta.textSize = 17f
        etiqueta.setTextColor(Color.WHITE)
        fondo.addView(etiqueta)

        titulo = EditText(this)
        titulo.hint = "Ej.: 5 hábitos que te hacen pobre"
        titulo.setTextColor(Color.WHITE)
        titulo.setHintTextColor(Color.GRAY)
        titulo.setSingleLine(true)
        titulo.inputType = InputType.TYPE_CLASS_TEXT or
                InputType.TYPE_TEXT_FLAG_CAP_SENTENCES
        fondo.addView(titulo)

        val etiquetaDuracion = TextView(this)
        etiquetaDuracion.text = "\nDuración del video"
        etiquetaDuracion.textSize = 17f
        etiquetaDuracion.setTextColor(Color.WHITE)
        fondo.addView(etiquetaDuracion)

        duracion = Spinner(this)
        val opciones = arrayOf(
            "1 minuto",
            "3 minutos",
            "5 minutos",
            "10 minutos"
        )
        duracion.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            opciones
        )
        fondo.addView(duracion)

        val boton = Button(this)
        boton.text = "CREAR GUION"
        fondo.addView(boton)

        resultado = TextView(this)
        resultado.textSize = 16f
        resultado.setTextColor(Color.WHITE)
        resultado.setPadding(0, 24, 0, 0)
        fondo.addView(resultado)

        boton.setOnClickListener {
            val tema = titulo.text.toString().trim()

            if (tema.isEmpty()) {
                Toast.makeText(
                    this,
                    "Escribe el tema de tu video",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                resultado.text = generarGuion(
                    tema,
                    duracion.selectedItem.toString()
                )
            }
        }

        val desplazamiento = ScrollView(this)
        desplazamiento.addView(fondo)
        setContentView(desplazamiento)
    }

    private fun generarGuion(tema: String, tiempo: String): String {
        val minutos = tiempo.substringBefore(" ").toInt()

        val introduccion =
            "¿Sabías que este tema puede cambiar tu forma de ver las cosas? " +
            "Hoy, Nico te contará todo lo que necesitas saber sobre $tema. " +
            "Quédate hasta el final porque lo más interesante viene después."

        val desarrollo = (1..minutos).joinToString("\n\n") { numero ->
            "ESCENA $numero\n" +
            "Nico aparece en pantalla y explica el punto número $numero " +
            "sobre $tema con un ejemplo sencillo, una situación cotidiana " +
            "y una conclusión que el espectador pueda recordar."
        }

        val cierre =
            "\n\nCIERRE\nNico resume las ideas principales y pregunta: " +
            "¿Qué opinas tú? Déjalo en los comentarios y suscríbete " +
            "para descubrir más curiosidades."

        return "GUION: $tema\nDuración elegida: $tiempo\n\n" +
                "INTRODUCCIÓN\n$introduccion\n\n$desarrollo$cierre"
    }
}
