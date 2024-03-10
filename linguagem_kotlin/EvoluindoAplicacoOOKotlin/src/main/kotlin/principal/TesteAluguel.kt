package principal

import factory.FactoryGame
import factory.FactoryGamer
import servico.aluguel.Aluguel
import servico.aluguel.PeriodoAluguel
import java.time.LocalDate

fun main(){
    val listaDeGamers = FactoryGamer.createGameByAPI()

    val gameAlugado = runCatching {
        FactoryGame.createByApiShark(146, null)
    }

    gameAlugado.onSuccess {
        val jogo = gameAlugado.getOrNull() // Batman: Arkham Asylum Game of the Year Edition
        val gamer = listaDeGamers.getOrNull(2) // Guilherme
        if(jogo != null && gamer != null ){
            val aluguel = gamer.alugarJogo(jogo = jogo, 20)
            println(aluguel)
            println("Lista de Alugueis")
            println(gamer.listDeAlugueis)
        }
    }
}