package com.example.imcapp
import androidx.appcompat.app.AlertDialog  // Importa la clase AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var Peso:EditText
    lateinit var Altura:EditText
    lateinit var btnCalcular:Button
    lateinit var lblMasa:TextView
    lateinit var btnBorrar:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Peso=findViewById(R.id.Peso)
        Altura=findViewById(R.id.Altura)
        btnCalcular=findViewById(R.id.btnCalcular)
        lblMasa=findViewById(R.id.lblMasa)
        btnBorrar=findViewById(R.id.btnBorrar)

        btnCalcular.setOnClickListener(View.OnClickListener {
            val pesoStr = Peso.text.toString().replace(',', '.')
            val alturaStr = Altura.text.toString().replace(',', '.')

            val peso = pesoStr.toFloatOrNull()
            val altura = alturaStr.toFloatOrNull()

            if (peso != null && altura != null) {
                val imc = calcular_imc(peso, altura)
                val mensaje = obtenerMensaje(imc)

                // Muestra la alerta en pantalla
                mostrarAlerta(mensaje)

                // Muestra el mensaje en la interfaz de usuario
                lblMasa.text = "El IMC es: $imc - $mensaje"
            } else {
                lblMasa.text = "Ingrese valores numéricos válidos para peso y altura."
            }
        })

        btnBorrar.setOnClickListener(){
            Peso.text.clear()
            Altura.text.clear()
            lblMasa.text=""
        }
    }

    fun obtenerMensaje(imc: Float): String {
        return when {
            imc < 18.5 -> "Actualmente estás bajo de peso. Deberías considerar aumentar tu peso para mantener una buena salud."
            imc >= 18.5 && imc <= 24.9 -> "Tu peso es normal. ¡Felicidades! Mantén hábitos saludables."
            imc >= 25.0 && imc <= 30 -> "Tienes sobrepeso. Deberías considerar cambiar tu dieta y aumentar tu actividad física."
            else -> "Tienes obesidad. Es importante que consultes a tu médico para recibir orientación y apoyo."
        }
    }

    fun mostrarAlerta(mensaje: String) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(mensaje)
            .setPositiveButton("Aceptar") { _, _ -> }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }



    fun calcular_imc(Peso: Float, Altura: Float): Float {
        return Peso / (Altura * Altura)
    }


}