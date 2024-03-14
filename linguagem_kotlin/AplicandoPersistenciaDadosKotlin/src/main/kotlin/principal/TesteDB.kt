package principal

import dados.Banco
import dados.dao.GamesDAO
import dados.entity.GameEntity
import modelo.game.Game

fun main() {
    val manager = Banco.getEntityManager()
    val gamesDAO = GamesDAO(manager)
    try {
        val jogo = Game(
            titulo = "Cloning Clyde",
            capa = "https://cdn.cloudflare.steamstatic.com/steam/apps/91800/capsule_sm_120.jpg?t=1447353946",
            preco = 1.24
        )
        //gamesDAO.add(jogo)
        gamesDAO.delete(4)

        val lista = gamesDAO.getLista()
        val jogoByID = gamesDAO.getById(1)

        println("testando getByID: $jogoByID\n\n----------\n" )
        lista.forEach { println(it) }
    } finally {
        manager.close()
    }
}