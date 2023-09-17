package com.example.zeronehundred

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.view.View.OnClickListener
import android.view.View.OnLongClickListener
import android.widget.Toast
import android.content.Intent
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    private lateinit var menorIntervalo: TextView
    private lateinit var maiorIntervalo: TextView
    private lateinit var mensagemSituacao: TextView
    private lateinit var chutarNumero: TextView
    private lateinit var botaoChutar: Button
    private var nomeJogador: String? = null
    private val jogo = Jogo()
    private val outraResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val nomeVencedor = result.data?.getStringExtra("nomeVencedor")
            if (nomeVencedor != null && nomeVencedor.isNotEmpty()) {
                // Atualize o texto do TextView "nomeVencedorTextView" com o nome do vencedor
                val nomeVencedorTextView = findViewById<TextView>(R.id.nomeVencedorTextView)
                nomeVencedorTextView.text = "Vencedor: $nomeVencedor"
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        menorIntervalo = findViewById(R.id.campoMenorIntervalo)
        maiorIntervalo = findViewById(R.id.campoMaiorIntervalo)
        mensagemSituacao = findViewById(R.id.mensagemSituacao)
        chutarNumero = findViewById(R.id.chutarNumero)
        botaoChutar = findViewById(R.id.botaoChutar)

        // Ao inicar o jogo, ja seta os valores iniciais.
        jogo.iniciarJogo()
        menorIntervalo.text = jogo.menorIntervalo.toString()
        maiorIntervalo.text = jogo.maiorIntervalo.toString()


        // Laço para validar sempre que o usuário apertar o botão, fazer a validação do número
         botaoChutar.setOnClickListener {
            // val chute = chutarNumero.text.toString().toInt()
             val chuteTexto = chutarNumero.text.toString()
             var chute = if (chuteTexto.isEmpty()) null else chuteTexto.toInt()
                if (chute == null) {
                    Toast.makeText(this, "Você não digitou um número!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }


            val situacao = jogo.validarNumero(chute)

            when (situacao) {
                Jogo.situacaoDoJogo.VITORIA -> {
                    Toast.makeText(this, "Você acertou!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, TelaVenceu::class.java)
                    outraResult.launch(intent)
                }
                Jogo.situacaoDoJogo.DERROTA -> {
                    Toast.makeText(this, "Você perdeu!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, TelaPerdeu::class.java)
                    startActivity(intent)
                }
                Jogo.situacaoDoJogo.EM_ANDAMENTO -> {
                    Toast.makeText(this, "Você errou!", Toast.LENGTH_SHORT).show()
                }
                Jogo.situacaoDoJogo.FORA_DO_INTERVALO -> {
                    Toast.makeText(this, "Você digitou um número fora do intervalo!", Toast.LENGTH_SHORT).show()
                }
            }
            menorIntervalo.text = jogo.menorIntervalo.toString()
            maiorIntervalo.text = jogo.maiorIntervalo.toString()
        }

        mensagemSituacao.setOnLongClickListener(OnLongClickListener {
            jogo.iniciarJogo()
            menorIntervalo.text = jogo.menorIntervalo.toString()
            maiorIntervalo.text = jogo.maiorIntervalo.toString()
            Toast.makeText(this, "Jogo reiniciado!", Toast.LENGTH_SHORT).show()
            true
        })
    }
}
