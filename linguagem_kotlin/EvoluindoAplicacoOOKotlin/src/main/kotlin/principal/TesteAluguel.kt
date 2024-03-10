package principal

import factory.FactoryGame
import factory.FactoryGamer
import modelo.planos.PlanoAssinatura
import modelo.usuario.Gamer

fun main(){
    val listaDeGamers = FactoryGamer.createGameByAPI()

    val gameAlugado = runCatching {
        FactoryGame.createByApiShark(146, null)
    }

    gameAlugado.onSuccess {
        val jogo = it // Batman: Arkham Asylum Game of the Year Edition
        val gamer = listaDeGamers.getOrNull(2) // Guilherme
        val gamer2 = listaDeGamers.getOrNull(3)
        if(gamer != null  && gamer2 != null){
            gamer.plano = PlanoAssinatura("Prata", desconto = 0.30, assinatura = 9.99, jogosIncluidos = 2 )

            val aluguelGamer1 = gamer.alugarJogo(jogo = jogo, 20)
            val aluguelGamer2 = gamer2.alugarJogo(jogo=jogo, 20)

            println("Diferença entre nossos planos:" +
                    "\n ${gamer.nome} do plano ${gamer.plano.tipo}:${aluguelGamer1}" +
                    "\n${gamer2.nome} do plano ${gamer2.plano.tipo}: $aluguelGamer2")
            println("Lista de Alugueis")

            println("${gamer.nome}: ${gamer.listDeAlugueis}\n")
            println("${gamer2.nome}: ${gamer2.listDeAlugueis}\n\n")

            val valorFinalPlanos: (Gamer) -> Double?
                    = { it.listDeAlugueis.map { it?.valorFinal?:0.0 }.reduce{ s, t -> s + t }}

            println("Diferença entre os planos: ${ (valorFinalPlanos(gamer2)?: 0.0) - (valorFinalPlanos(gamer)?: 0.0)}")
        }
    }
}