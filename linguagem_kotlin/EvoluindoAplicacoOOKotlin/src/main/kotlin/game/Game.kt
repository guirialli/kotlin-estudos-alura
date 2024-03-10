package game

import utilitarios.toBRL
import java.text.NumberFormat
import java.util.*

@Suppress("DEPRECATION")
class Game(
   val titulo: String,
   val capa: String,
   val preco: Double,
    var descricao: String? = null
) {

    override fun toString(): String {
        return "Game: \nTitulo $titulo \nCapa: $capa  \nDescricao: $descricao\nPreço: ${preco.toBRL()} "
    }

}