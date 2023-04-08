package com.example.proyecto_1_v2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


// Esta clase se utiliza para mostrar una pantalla de bienvenida
// durante un corto período de tiempo antes de dirigirse a la
// actividad principal.

class SplashActivity : AppCompatActivity() {

    // Constante que indica la cantidad de tiempo en milisegundos
    // que se muestra la pantalla de bienvenida.
    private val SPLASH_TIME_OUT: Long = 3000 // 3 segundos

    // Método que se llama cuando se crea la actividad
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Se establece el diseño de la actividad a partir de un archivo XML
        setContentView(R.layout.activity_splash)

        // El siguiente código inicia una tarea en segundo plano utilizando
        // corutinas, que se ejecutará durante el tiempo especificado en la
        // constante SPLASH_TIME_OUT. Luego, se inicia la actividad principal
        // y se finaliza la actividad actual.
        GlobalScope.launch {
            delay(SPLASH_TIME_OUT)
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }
    }
}