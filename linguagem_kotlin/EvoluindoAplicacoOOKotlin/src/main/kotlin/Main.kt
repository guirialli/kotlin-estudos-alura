package org.example

import org.example.factory.FactoryGame
import org.example.game.Game
import org.example.usuario.Gamer
import java.util.*

fun main() {
    val sc = Scanner(System.`in`)
    var continuar: String
    val gamer = Gamer.creatGame(sc)

    do {
        print("Digite o id do jogo: ")
        val keyGame = runCatching {
            sc.nextInt()
        }
        sc.nextLine()

        keyGame.onFailure {
            println("O id deve ser um número inteiro!")
        }

        keyGame.onSuccess {
            val idGame = keyGame.getOrNull()
            if (idGame != null) {
                val game = runCatching {
                    FactoryGame.createByApiShark(idGame, null)
                }

                game.onSuccess {
                    val gameSuccess = game.getOrNull()
                    if (gameSuccess != null) {
                        println("Você deseja adicionar uma descrição personalizada para o jogo: ${gameSuccess.titulo}?")

                        //Verificando se o usuário quer digitar um título personalizado
                        val op = sc.nextLine()
                        if (op.equals("s", true) || op.equals("sim", true)) {
                            println("Digite a descrição que quer: ")
                            val descriptor = sc.nextLine()
                            gameSuccess.descricao = descriptor
                        }
                        println("----Dados do Jogo----\n")
                        println(gameSuccess)
                        gamer.gamesSearched.add(gameSuccess)
                    }
                }
                game.onFailure {
                    println("O jogo procurado não existe!")
                }
            }
        }
        println()
        println("Você deseja buscar outro jogo?")
        continuar = sc.nextLine()
    } while (continuar.equals("s", true) || continuar.equals("sim", true))

    println("\nJogos Buscados:")
    println(gamer.gamesSearched)

    //Arrow Function printar jogo
    val printarJogos: (Game?) -> Unit = {
        println("Titulo: ${it?.titulo}\n Descrição: ${it?.descricao}\n")
    }

    //Ordenando jogos
    println("Jogos ordenados por titulo:")
    gamer.gamesSearched.sortBy { it?.titulo }
    gamer.gamesSearched.forEach { printarJogos(it) }

    println()

    // filtrando jogos
    val jogosFiltrados = gamer.gamesSearched.filter {
        it?.titulo?.contains("batman", true) ?: false
    }
    println("Jogos filtrados por batman:\n")
    jogosFiltrados.forEach { printarJogos(it) }

    println("Busca de jogos finalizadas com Sucesso!")
}
