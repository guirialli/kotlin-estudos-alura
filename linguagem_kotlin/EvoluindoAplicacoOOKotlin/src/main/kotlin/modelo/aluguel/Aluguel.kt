package modelo.aluguel

import modelo.game.Game
import modelo.usuario.Gamer
import utilitarios.toBRL
import java.util.UUID

class Aluguel(
    val gamer: Gamer,
    val jogo: Game,
    val periodo: PeriodoAluguel,
) {
    private val uid: String = UUID.randomUUID().toString()
    val valorFinal: Double = gamer.plano.calcularAluguel(this)
    override fun toString(): String {
        return "\nAluguel ${uid}:" +
                "\nJogo: ${jogo.titulo}\n" +
                "Alugado para: ${gamer.nome}\n" +
                "$periodo" +
                "Valor final: ${valorFinal.toBRL()}\n"
    }
}