package com.example.zeronehundred

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.view.View.OnClickListener
import android.view.View.OnLongClickListener

class MainActivity : AppCompatActivity() {
    private lateinit var menorIntervalo: TextView
    private lateinit var maiorIntervalo: TextView
    private lateinit var mensagemSituacao: TextView
    private lateinit var chutarNumero: TextView
    private lateinit var botaoChutar: Button
    private val jogo = Jogo()

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
                    mensagemSituacao.text = "Você não digitou um número!"
                    return@setOnClickListener
                }

            val situacao = jogo.validarNumero(chute)

            when (situacao) {
                Jogo.situacaoDoJogo.VITORIA -> {
                    mensagemSituacao.text = "Você acertou!"
                }
                Jogo.situacaoDoJogo.DERROTA -> {
                    mensagemSituacao.text = "Você perdeu!"
                }
                Jogo.situacaoDoJogo.EM_ANDAMENTO -> {
                    mensagemSituacao.text = "Você errou!"
                }
                Jogo.situacaoDoJogo.FORA_DO_INTERVALO -> {
                    mensagemSituacao.text = "Você digitou um número fora do intervalo!"
                }
            }
            menorIntervalo.text = jogo.menorIntervalo.toString()
            maiorIntervalo.text = jogo.maiorIntervalo.toString()
        }

        botaoChutar.setOnLongClickListener(OnLongClickListener {
            jogo.iniciarJogo()
            menorIntervalo.text = jogo.menorIntervalo.toString()
            maiorIntervalo.text = jogo.maiorIntervalo.toString()
            mensagemSituacao.text = "Jogo reiniciado!"
            true
        })
    }
}
