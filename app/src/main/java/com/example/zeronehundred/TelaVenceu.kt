package com.example.zeronehundred
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View
import android.widget.EditText

class TelaVenceu : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_venceu)
    }

    fun jogarNovamente(view: View) {
        val edtNomeVencedor = findViewById<EditText>(R.id.edtNomeVencedor)
        val nomeVencedor = edtNomeVencedor.text.toString()

        val intent = Intent()
        intent.putExtra("nomeVencedor", nomeVencedor)
        setResult(RESULT_OK, intent)
        finish()
    }
}