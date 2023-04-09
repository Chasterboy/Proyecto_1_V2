package com.example.proyecto_1_v2

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import com.example.proyecto_1_v2.databinding.ActivityMainBinding
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

class ResultActivity : AppCompatActivity() {
    // Esta función se ejecuta cuando se crea la actividad
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("StringFormatInvalid", "StringFormatMatches")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        // Recuperar los datos pasados como extras desde la actividad anterior
        val email = intent.getStringExtra("email")
        val nombre = intent.getStringExtra("nombre")
        val apellido = intent.getStringExtra("apellido")
        val Ncuenta = intent.getStringExtra("NCuenta")
        val carreraSeleccionada = intent.getStringExtra("Carrera")
        val fecha = intent.getStringExtra("FechaNaci")

        // Obtener los TextViews en los que se van a mostrar los resultados
        val textNombre = findViewById<TextView>(R.id.textNombre)
        val textCarrera = findViewById<TextView>(R.id.textCarrera)
        val NcuentaT = findViewById<TextView>(R.id.textNumcuenta)
        val Correo = findViewById<TextView>(R.id.textCorreo)
        val FechaNacimiento = findViewById<TextView>(R.id.textEdad)
        val Zodiaco = findViewById<TextView>(R.id.textZodiaco)
        val Chino = findViewById<TextView>(R.id.textChino)

        // Mostrar los datos en los TextViews
        textNombre.text = getString(R.string.nombre_result, nombre, apellido)
        textCarrera.text = getString(R.string.carrera_result, carreraSeleccionada)
        NcuentaT.text = getString(R.string.n_de_cuenta_result, Ncuenta)
        Correo.text = getString(R.string.correo_result, email)

        // Obtener la fecha actual
        val fechaActual = LocalDate.now()

        // Formatear la fecha de nacimiento y convertirla a LocalDate
        val formatter = DateTimeFormatter.ofPattern("d/M/yyyy")
        val fecha2 = LocalDate.parse(fecha, formatter)
        val formatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val fechaFormateada = fecha2.format(formatter2)
        val fechaNacimiento = LocalDate.parse(fechaFormateada, formatter2)

        // Calcular la edad en años
        val edad = Period.between(fechaNacimiento, fechaActual).years

        // Mostrar la edad en el TextView correspondiente
        FechaNacimiento.text = getString(R.string.edad_result, edad)

        // Calcular y mostrar el signo zodiacal en el TextView correspondiente
        val signo = getSignoZodiacal(fechaNacimiento)
        Zodiaco.text = getString(R.string.signo_zodiacal_result, signo)

        // Obtener el año de nacimiento
        val anioNacimiento = fechaNacimiento.year

        // Calcular el signo del horóscopo chino y mostrarlo en el TextView correspondiente
        val signoChino = when (anioNacimiento % 12) {
            0 -> "Mono"
            1 -> "Gallo"
            2 -> "Perro"
            3 -> "Cerdo"
            4 -> "Rata"
            5 -> "Buey"
            6 -> "Tigre"
            7 -> "Conejo"
            8 -> "Dragón"
            9 -> "Serpiente"
            10 -> "Caballo"
            else -> "Cabra"
        }
        Chino.text = getString(R.string.horoscopo_chino_result, signoChino)


    }
}
@RequiresApi(Build.VERSION_CODES.O)
fun getSignoZodiacal(fechaNacimiento: LocalDate): String {
    val dia = fechaNacimiento.dayOfMonth
    val mes = fechaNacimiento.monthValue
    return when {
        (dia >= 21 && mes == 3) || (dia <= 20 && mes == 4) -> "Aries"
        (dia >= 21 && mes == 4) || (dia <= 21 && mes == 5) -> "Tauro"
        (dia >= 22 && mes == 5) || (dia <= 21 && mes == 6) -> "Géminis"
        (dia >= 22 && mes == 6) || (dia <= 23 && mes == 7) -> "Cáncer"
        (dia >= 24 && mes == 7) || (dia <= 23 && mes == 8) -> "Leo"
        (dia >= 24 && mes == 8) || (dia <= 23 && mes == 9) -> "Virgo"
        (dia >= 24 && mes == 9) || (dia <= 23 && mes == 10) -> "Libra"
        (dia >= 24 && mes == 10) || (dia <= 22 && mes == 11) -> "Escorpio"
        (dia >= 23 && mes == 11) || (dia <= 21 && mes == 12) -> "Sagitario"
        (dia >= 22 && mes == 12) || (dia <= 20 && mes == 1) -> "Capricornio"
        (dia >= 21 && mes == 1) || (dia <= 19 && mes == 2) -> "Acuario"
        else -> "Piscis"
    }
}