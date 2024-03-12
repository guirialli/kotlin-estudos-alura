package principal

import dados.JogosDAO
import modelo.game.Game

fun main(){
    val jogo = Game(
        titulo = "Depths of Peril",
        preco = 1.69,
        capa = "https://cdn.cloudflare.steamstatic.com/steam/apps/23600/capsule_sm_120.jpg?t=1536792253"
    )
    JogosDAO.addJogo(jogo)
    val lista = JogosDAO.getJogos()
    lista.forEach { println(it) }
}