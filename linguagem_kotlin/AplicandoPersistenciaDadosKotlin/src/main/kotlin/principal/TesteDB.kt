package principal

import dados.Banco
import dados.dao.GamesDAO
import modelo.game.Game

fun main() {
    val manager = Banco.getEntityManager()
    val gamesDAO = GamesDAO(manager)
    try {
        val jogo = Game(
            titulo = "Cloning Clyde",
            capa = "https://cdn.cloudflare.steamstatic.com/steam/apps/91800/capsule_sm_120.jpg?t=1447353946",
            preco = 1.24,
            descricao = "Cloning Clyde é um jogo de plataforma em 2D desenvolvido pela NinjaBee e lançado em 19 de julho de 2006 para o Xbox Live Arcade do Xbox 360 e em 15 de março de 2011 para Microsoft Windows. Neste jogo, um grupo de clones idênticos, todos conhecidos como “Clyde”, trabalha juntos para tentar escapar do laboratório em que estão presos. A jogabilidade envolve alternar entre os clones Clyde para resolver quebra-cabeças e superar obstáculos. Além disso, os Clydes podem fundir seu DNA com animais ou objetos inanimados para obter novas habilidades. Prepare-se para uma aventura hilária e cheia de ação enquanto luta contra armadilhas sinistras e mutações!"

        )
        gamesDAO.addJogo(jogo)
        val lista = gamesDAO.getJogos()
        manager.close()
        lista.forEach { println(it) }
    } finally {
        manager.close()
    }
}