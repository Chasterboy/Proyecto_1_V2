package com.example.proyecto_1_v2


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.proyecto_1_v2.databinding.ActivityMainBinding
import java.util.TimerTask

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var diaSeleccionado = 0
    private var mesSeleccionado = 0
    private var anioSeleccionado = 0
    private var carreraSeleccionada = "" // variable para almacenar la carrera seleccionada

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // Inflar el layout de la actividad usando DataBindingUtil
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Configurar el listener para el botón de fecha de nacimiento
        binding.FechaNacText.setOnClickListener { showDatePickerDialog() }

        // Configurar el adaptador para la lista de carreras
        val carreras = resources.getStringArray(R.array.carreras)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, carreras)
        binding.listviewCarreras.adapter = adapter

        // Configurar el listener para el spinner de carreras
        binding.listviewCarreras.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // Almacenar la carrera seleccionada
                carreraSeleccionada = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    // Función para mostrar el diálogo de selección de fecha
    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(supportFragmentManager, "FechaNac")
    }

    // Función para actualizar la fecha seleccionada en el EditText
    fun onDateSelected(day: Int, month: Int, year: Int) {
        diaSeleccionado = day
        mesSeleccionado = month + 1
        anioSeleccionado = year
        binding.FechaNacText.setText("$day/$mesSeleccionado/$year")
    }

    // Función para validar una dirección de correo electrónico
    fun validarCorreoElectronico(correo: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(correo).matches()
    }

    // Función que se llama al presionar el botón "Enviar"
    fun BEnviar(view: View) {
        // Obtener los datos ingresados en los campos de texto
        val email = binding.CorreoText.text.toString()
        val nombre = binding.nombreText.text.toString()
        val apellido = binding.ApellidosText.text.toString()
        val Ncuenta = binding.NoCuentaText.text.toString()
        var fecha = binding.FechaNacText.text.toString()

        val camposFaltantes = mutableListOf<String>()



        // Imprimir la fecha de nacimiento obtenida (para fines de prueba)
        //println("Fecha de nacimiento es: $fecha")

        // Validar si el campo de correo electrónico está vacío
        if (email.isEmpty()) {
            binding.CorreoText.error = "Ingrese un correo electrónico"
            camposFaltantes.add("CorreoElectronico")

        } else {
            // Si el campo de correo electrónico no está vacío, validar si es válido
            if (validarCorreoElectronico(email)) {
                // Si el correo electrónico es válido, agregar acciones a realizar aquí

            } else {
                // Si el correo electrónico no es válido, mostrar un mensaje de error
                binding.CorreoText.error = "Correo electrónico no válido"
                camposFaltantes.add("CorreoElectronico")
            }
        }

        // Validar si el campo de nombre está vacío
        if (nombre.isEmpty()) {
            binding.nombreText.error = "Ingrese tu nombre"
            camposFaltantes.add("Nombre")
        }

        // Validar si el campo de apellido está vacío
        if (apellido.isEmpty()) {
            binding.ApellidosText.error = "Ingrese tus apellidos"
            camposFaltantes.add("Apellido")

        }

        // Validar si el campo de número de cuenta está vacío
        if (Ncuenta.isEmpty()) {
            binding.NoCuentaText.error = "Ingrese tu N° de cuenta"
            camposFaltantes.add("numCuenta")

        } else {
            // Si el campo de número de cuenta no está vacío, validar si tiene una longitud válida
            if (Ncuenta.length == 9) {
                // Si el número de cuenta es válido, agregar acciones a realizar aquí
            } else {
                // Si el número de cuenta no es válido, mostrar un mensaje de error
                binding.NoCuentaText.error = "N° de cuenta no válido"
                camposFaltantes.add("numCuenta")

            }
        }

        // Validar si se ha seleccionado una carrera
        if (carreraSeleccionada.isEmpty()) {
            Toast.makeText(this, "Seleccione una carrera", Toast.LENGTH_SHORT).show()
            camposFaltantes.add("CarreraSeleccionada")

        } else {
            // Si se ha seleccionado una carrera, agregar acciones a realizar aquí
        }

        // Validar si el campo de fecha de nacimiento está vacío
        if (fecha.isEmpty()) {
            Toast.makeText(this, "Ingrese su fecha de nacimiento", Toast.LENGTH_SHORT).show()
            camposFaltantes.add("fechanacimiento")

        }
        //println("---------->$camposFaltantes<------------------")
        //Condicion para verificar si todos los datos son correctos
        if (!camposFaltantes.isNotEmpty()) {
            //val mensaje = "todos los campos se llenaron adecuadamente "
            //Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
            val intent = Intent(this, ResultActivity::class.java)
            val bundle = Bundle()

            bundle.putString("nombre", nombre)
            bundle.putString("apellido", apellido)
            bundle.putString("email", email)
            bundle.putString("NCuenta", Ncuenta)
            bundle.putString("FechaNaci", fecha)
            bundle.putString("Carrera", carreraSeleccionada)

            intent.putExtras(bundle)


            startActivity(intent)
        }
    }
}

