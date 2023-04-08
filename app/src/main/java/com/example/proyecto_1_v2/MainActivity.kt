package com.example.proyecto_1_v2


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
        //setContentView(R.layout.activity_main)

        //condiciones del splash


        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.FechaNacText.setOnClickListener{showDatePickerDialog()}

        setContentView(binding.root)
        val carreras = resources.getStringArray(R.array.carreras)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, carreras)
        binding.listviewCarreras.adapter = adapter


        binding.listviewCarreras.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                carreraSeleccionada = parent?.getItemAtPosition(position).toString()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

    }

    private fun showDatePickerDialog(){
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(supportFragmentManager, "FechaNac")
    }

    fun onDateSelected(day:Int, month:Int, year:Int){
        diaSeleccionado = day
        mesSeleccionado = month + 1
        anioSeleccionado = year
        binding.FechaNacText.setText("$day / $mesSeleccionado  / $year")
    }
    fun validarCorreoElectronico(correo: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(correo).matches()
    }
    fun BEnviar(view: View) {
        val email = binding.CorreoText.text.toString()
        val nombre = binding.nombreText.text.toString()
        val apellido = binding.ApellidosText.text.toString()
        val Ncuenta = binding.NoCuentaText.text.toString()
//        val Carrera = findViewById<Spinner>(R.id.mi_spinner)
        var fecha  = binding.FechaNacText.text.toString()

        println("Fecha de nacmiento es: $fecha ------------------------------------------------------------------------------")

        if (email.isEmpty()) {
            binding.CorreoText.error = "Ingrese un correo electrónico"

        } else {
            if(validarCorreoElectronico(email)){
                //incresar acciones a realizar
            }else{

                binding.CorreoText.error = "Correo electrónico no valido"

            }

        }

        if (nombre.isEmpty()) {
            binding.nombreText.error = "Ingrese tu nombre"

        }
        if (apellido.isEmpty()) {
            binding.ApellidosText.error = "Ingrese tus apellidos"

        }
        if (Ncuenta.isEmpty()) {
            binding.NoCuentaText.error = "Ingrese tu N° de cuenta "

        }else{
            if(Ncuenta.length == 9){
                //incresar acciones a realizar
            }else{

                binding.NoCuentaText.error = "N° de cuenta no valido "

            }

        }

        println("Carrera selecionada es: $carreraSeleccionada -----------------------\n")
        if (carreraSeleccionada.isEmpty()) {
            Toast.makeText(this, "Seleccione una carrera", Toast.LENGTH_SHORT).show()
        } else {
            // acciones a realizar con la carrera seleccionada
        }
        if (fecha.isEmpty()) {
            Toast.makeText(this, "Ingrese su fecha de nacimiento", Toast.LENGTH_SHORT).show()

        }


    }

    //para validar correo

}

