package com.example.proyecto_1_v2

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
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
        FechaNacimiento.text = resources.getQuantityString(R.plurals.edad_result, edad, edad);

        // Calcular y mostrar el signo zodiacal en el TextView correspondiente

        val dia = fechaNacimiento.dayOfMonth
        val mes = fechaNacimiento.monthValue
        val signo = when {
            (dia >= 21 && mes == 3) || (dia <= 20 && mes == 4) -> getString(R.string.Aries)
            (dia >= 21 && mes == 4) || (dia <= 21 && mes == 5) -> getString(R.string.Tauro)
            (dia >= 22 && mes == 5) || (dia <= 21 && mes == 6) -> getString(R.string.Geminis)
            (dia >= 22 && mes == 6) || (dia <= 23 && mes == 7) -> getString(R.string.Cancer)
            (dia >= 24 && mes == 7) || (dia <= 23 && mes == 8) -> getString(R.string.Leo)
            (dia >= 24 && mes == 8) || (dia <= 23 && mes == 9) -> getString(R.string.Virgo)
            (dia >= 24 && mes == 9) || (dia <= 23 && mes == 10) -> getString(R.string.Libra)
            (dia >= 24 && mes == 10) || (dia <= 22 && mes == 11) -> getString(R.string.Escorpio)
            (dia >= 23 && mes == 11) || (dia <= 21 && mes == 12) -> getString(R.string.Sagitario)
            (dia >= 22 && mes == 12) || (dia <= 20 && mes == 1) -> getString(R.string.Capricornio)
            (dia >= 21 && mes == 1) || (dia <= 19 && mes == 2) -> getString(R.string.Acuario)
            else -> getString(R.string.Piscis)
        }
        Zodiaco.text = getString(R.string.signo_zodiacal_result, signo)

        // Obtener el año de nacimiento
        val anioNacimiento = fechaNacimiento.year

        // Calcular el signo del horóscopo chino y mostrarlo en el TextView correspondiente
        val signoChino = when (anioNacimiento % 12) {
            0 -> getString(R.string.Mono)
            1 -> getString(R.string.Gallo)
            2 -> getString(R.string.Perro)
            3 -> getString(R.string.Cerdo)
            4 -> getString(R.string.Rata)
            5 -> getString(R.string.Buey)
            6 -> getString(R.string.Tigre)
            7 -> getString(R.string.Conejo)
            8 -> getString(R.string.Dragon)
            9 -> getString(R.string.Serpiente)
            10 -> getString(R.string.Serpiente)
            else -> getString(R.string.Cabra)
        }
        Chino.text = getString(R.string.horoscopo_chino_result, signoChino)


        val imageView = findViewById<ImageView>(R.id.imageCarrera)

        when (carreraSeleccionada) {
            getString(R.string.IngCivil) -> imageView.setImageResource(R.drawable.civil)
            getString(R.string.IngEE) -> imageView.setImageResource(R.drawable.electronico)
            getString(R.string.IngInd) -> imageView.setImageResource(R.drawable.industrial)
            getString(R.string.IngCom) -> imageView.setImageResource(R.drawable.computacion)
            getString(R.string.IngTele) -> imageView.setImageResource(R.drawable.telecomunicaciones)
            getString(R.string.IngMec) -> imageView.setImageResource(R.drawable.mecanica)
            getString(R.string.IngMecatro) -> imageView.setImageResource(R.drawable.mecatronico)
            getString(R.string.IngPetro) -> imageView.setImageResource(R.drawable.petrolero)
            getString(R.string.IngAero) -> imageView.setImageResource(R.drawable.aeroespacial)
            getString(R.string.IngGeo) -> imageView.setImageResource(R.drawable.geomatico)
            getString(R.string.IngAmb) -> imageView.setImageResource(R.drawable.ambiental)
            getString(R.string.IngGeoFis) -> imageView.setImageResource(R.drawable.geofisica)
            getString(R.string.IngGeolo) -> imageView.setImageResource(R.drawable.geologia)
            getString(R.string.IngMM) -> imageView.setImageResource(R.drawable.minas)
            getString(R.string.IngSB) -> imageView.setImageResource(R.drawable.biomedico)
            else -> imageView.setImageResource(R.drawable.cheems)
        }


    }
}
