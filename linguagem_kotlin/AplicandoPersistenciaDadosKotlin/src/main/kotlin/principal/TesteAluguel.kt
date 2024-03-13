package principal

import com.google.gson.GsonBuilder
import modelo.factory.FactoryGame
import modelo.factory.FactoryGamer
import modelo.planos.PlanoAssinatura
import modelo.usuario.Gamer
import utilitarios.toBRL
import java.io.File

fun main() {
    val listaDeGamers = FactoryGamer.createGameByAPI()

    val gameAlugado = runCatching {
        FactoryGame.createByApiShark(146, null)
    }

    gameAlugado.onSuccess {
        val jogo = it // Batman: Arkham Asylum Game of the Year Edition
        val gamer = listaDeGamers.getOrNull(2) // Guilherme
        val gamer2 = listaDeGamers.getOrNull(3)
        if (gamer != null && gamer2 != null) {
            gamer.plano = PlanoAssinatura("Prata", desconto = 0.30, assinatura = 9.99, jogosIncluidos = 2)
            gamer.recomendar(5)
            gamer2.recomendar(4)

            gamer.alugarJogo(jogo, 20)
            gamer.alugarJogo(jogo, 20)

            val aluguelGamer1 = gamer.alugarJogo(jogo = jogo, 20)
            val aluguelGamer2 = gamer2.alugarJogo(jogo = jogo, 20)

            println(
                "Diferença entre nossos planos:" +
                        "\n ${gamer.nome} do plano ${gamer.plano.tipo}:${aluguelGamer1}" +
                        "\n${gamer2.nome} do plano ${gamer2.plano.tipo}: $aluguelGamer2"
            )
            println("Lista de Alugueis")

            println("${gamer.nome}: ${gamer.listDeAlugueis}\n")
            println("${gamer2.nome}: ${gamer2.listDeAlugueis}\n\n")

            val valorFinalPlanos: (Gamer) -> Double? =
                { it.listDeAlugueis.map { it?.valorFinal ?: 0.0 }.reduce { s, t -> s + t } }

            println(
                "Com o plano prata você economiza: " +
                        "${((valorFinalPlanos(gamer2) ?: 0.0) - (valorFinalPlanos(gamer) ?: 0.0)).toBRL()} " +
                        "alugando mais!"
            )

            // Vamos trabalhar com serialização de algum dado
            gamer.avaliarJogo(jogo, 10)
            val gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
            val serializacao = gson.toJson(gamer.jogosRecomendados)
            println("Resultado da serialização:\n$serializacao")

            val arquivo = File("jogosRecomendados-${gamer.nome}.json")
            arquivo.writeText(serializacao)
            println(arquivo.absolutePath)

        }
    }
}