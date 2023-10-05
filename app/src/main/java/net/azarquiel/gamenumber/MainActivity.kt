package net.azarquiel.gamenumber

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import kotlin.random.Random


class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var random: Random
    private lateinit var tvintentos: TextView
    private lateinit var tvmayormenor: TextView
    private lateinit var tvnumber: TextView
    var numberazar: Int = 0 //son contadores
    var number = 0
    var intentos = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        random = Random(System.currentTimeMillis())

       //numberazar = (1..100).random() //si pones entre parentesis 1..100 sale eso

        tvintentos = findViewById<TextView>(R.id.tvintentos)
        tvmayormenor = findViewById<TextView>(R.id.tvmayormenor)
        tvnumber = findViewById<TextView>(R.id.tvnumber)
        //alt + enter crea el private fun debajo
        // ctrl + d duplica la línea debajo

        var llbotones = findViewById<LinearLayout>(R.id.llbotones)
        for (i in 0..<llbotones.childCount) {
            var btn = llbotones.getChildAt(i)
            btn.setOnClickListener(this)
        }
        newGame()
    }

        override fun onClick(v: View?) {
            val btn = v as Button
            val n = (btn.tag as String).toInt()
            if ((number + n) < 1 || (number + n) > 100)
                return
            intentos++
            number += n
            show()
            check()
        }

        private fun check() {
            var cadena = ""
            if (number == numberazar) {
                gameOver()

            } else if (number > numberazar) {
                cadena = "Menor. Sigue probando"

            } else {
                cadena = "Mayor. Sigue probando"
            }
            tvmayormenor.text = cadena
        }

        private fun gameOver() {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Reiniciar partida")
            builder.setMessage("Lo conseguistes en $intentos intentos.")
            builder.setPositiveButton("New Game") { dialog, which ->
                newGame()
            }
            builder.setNegativeButton("Exit") { dialog, which ->
                finish()
            }
            builder.show()
        }

        private fun newGame() {
            numberazar = (1..100).random(random)
            intentos = 0
            number = 50
            show()
        }

        private fun show() {
            tvnumber.text = "$number"
            tvintentos.text = "Intentos: $intentos"
        }
}