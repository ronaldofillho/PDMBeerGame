package com.example.zeronehundred
import kotlin.random.Random

class Jogo {

    var numeroRandomico: Int = 0
    var menorIntervalo: Int = 0
    var maiorIntervalo: Int = 0
    var numeroAcerto: Int = 0

    // Enum para definir a situação do jogo
    enum class situacaoDoJogo {
        VITORIA,
        DERROTA,
        EM_ANDAMENTO,
        FORA_DO_INTERVALO
    }

    // Inicializa o jogo
    fun iniciarJogo() {
        numeroRandomico = Random.nextInt(1, 500)
        menorIntervalo = numeroRandomico
        maiorIntervalo = numeroRandomico + 100
        numeroAcerto = Random.nextInt(menorIntervalo, maiorIntervalo)
    }

    // Valida o número inserido pelo usuário
    fun validarNumero(chute: Int): situacaoDoJogo {
        if (chute < menorIntervalo || chute > maiorIntervalo) {
            iniciarJogo()
            return situacaoDoJogo.DERROTA
        } else if (chute == numeroAcerto) {
            return situacaoDoJogo.VITORIA
        } else if (chute < numeroAcerto) {
            menorIntervalo = chute +1
        } else if (chute > numeroAcerto) {
            maiorIntervalo = chute -1
        }
        if (menorIntervalo == maiorIntervalo) {
            return situacaoDoJogo.DERROTA
        }
        return situacaoDoJogo.EM_ANDAMENTO
    }
}