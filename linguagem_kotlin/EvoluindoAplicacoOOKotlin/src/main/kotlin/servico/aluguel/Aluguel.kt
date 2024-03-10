package servico.aluguel

import game.Game
import org.example.usuario.Gamer
import utilitarios.toBRL
import java.time.LocalDate
import java.time.Period
import java.util.UUID

class Aluguel(
    val gamer: Gamer,
    val jogo: Game,
    val periodo: PeriodoAluguel,
    val valorFinal: Double = jogo.preco * periodo.dias
) {
    private val uid: String = UUID.randomUUID().toString()

    override fun toString(): String {
        return "\nAluguel ${uid}:" +
                "\nJogo: ${jogo.titulo}\n" +
                "Alugado para: ${gamer.nome}\n" +
                "$periodo" +
                "Valor final: ${valorFinal.toBRL()}\n"
    }
}