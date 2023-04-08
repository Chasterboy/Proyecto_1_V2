package com.example.proyecto_1_v2

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class DatePickerFragment(val listener: (day:Int, month:Int, year:Int) -> Unit) : DialogFragment(),
    DatePickerDialog.OnDateSetListener {

    // El método onDateSet se llama cuando el usuario selecciona una fecha en el diálogo del selector de fecha.
    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, dayofMonth: Int) {
        // Llamamos al listener que se pasó al fragmento con la fecha seleccionada.
        listener(dayofMonth, month, year)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c: Calendar = Calendar.getInstance()
        val day: Int = c.get(Calendar.DAY_OF_MONTH)
        val month: Int = c.get(Calendar.MONTH)
        val year: Int = c.get(Calendar.YEAR)
        // Creamos el diálogo del selector de fecha con la fecha actual por defecto.
        val picker = DatePickerDialog(activity as Context, this, year, month, day)
        // Devolvemos el diálogo creado.
        return picker
    }
}