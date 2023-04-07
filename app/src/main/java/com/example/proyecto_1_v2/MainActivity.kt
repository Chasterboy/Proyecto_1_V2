package com.example.proyecto_1_v2


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.example.proyecto_1_v2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.FechaNacText.setOnClickListener{showDatePickerDialog()}

        setContentView(binding.root)
        val carreras = resources.getStringArray(R.array.carreras)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, carreras)
        binding.listviewCarreras.adapter = adapter

    }

    private fun showDatePickerDialog(){
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(supportFragmentManager, "FechaNac")
    }

    fun onDateSelected(day:Int, month:Int, year:Int){
        binding.FechaNacText.setText("$day / $month / $year")
    }
}