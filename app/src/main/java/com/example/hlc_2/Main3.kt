package com.example.hlc_2

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.hlc_2.databinding.ActivityMain3Binding

class Main3 : AppCompatActivity(), View.OnClickListener {
    private var binding: ActivityMain3Binding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        binding = ActivityMain3Binding.inflate(layoutInflater)
        val view: View = binding!!.root
        setContentView(view)
        binding!!.botonMostrar.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if (view === binding!!.botonMostrar) {
            try {
                var numero1 = Integer.valueOf(binding!!.editTextNumero1.text.toString())
                var numero2 = Integer.valueOf(binding!!.editTextNumero2.text.toString())
                var resultado = ""
                if (numero2 < numero1) {
                    var temp = 0
                    temp = numero2
                    numero2 = numero1
                    numero1 = temp
                }
                while (numero1 < numero2) {
                    var flag = false
                    for (i in 2..numero1 / 2) {
                        // condition for nonprime number
                        if (numero1 % i == 0) {
                            flag = true
                            break
                        }
                    }
                    if (!flag && numero1 != 0 && numero1 != 1) {
                        resultado = "$resultado$numero1 "
                        println(numero1)
                    }
                    ++numero1
                }
                println(resultado)
                binding!!.textViewNumeros.text = resultado
            } catch (e: NumberFormatException) {
                Toast.makeText(this, "Introduce dos numeros validos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}