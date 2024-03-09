package org.example

import org.example.factory.FactoryGame
import java.util.*

fun main() {
    val sc = Scanner(System.`in`)
    print("Digite o id do jogo: ")
    val keyGame = runCatching {
        sc.nextInt()
    }

    keyGame.onFailure {
        println("O id deve ser um número inteiro!")
    }

    keyGame.onSuccess {
        val idGame = keyGame.getOrNull()
        if (idGame != null) {
            val game = runCatching {
                FactoryGame().createByApiShark(idGame, null)
            }

            game.onSuccess {
                val gameSuccess = game.getOrNull()
                if (gameSuccess != null) {
                    println("Você deseja adicionar uma descrição personalizada para o jogo: ${gameSuccess.titulo}")
                    sc.nextLine()

                    //Verificando se o usuário quer digitar um título personalizado
                    val op =  sc.nextLine()
                    if(op.equals("s", true) || op.equals("sim", true)){
                        println("Digite a descrição que quer: ")
                        val descriptor = sc.nextLine()
                        gameSuccess.descricao = descriptor
                    }
                    println("----Dados do Jogo----\n")
                    println(gameSuccess)
                }
            }
            game.onFailure {
                println("O jogo procurado não existe!")
            }
        }
    }
}
