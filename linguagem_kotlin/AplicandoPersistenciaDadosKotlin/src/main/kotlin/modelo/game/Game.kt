package modelo.game

import com.google.gson.annotations.Expose
import modelo.recomendacao.Recomendavel
import utilitarios.toBRL

class Game(
    @Expose val titulo: String,
    @Expose val capa: String,
    @Expose var id: Int = 1,
    val preco: Double,
    var descricao: String? = null,
) : Recomendavel{
    private val listaAvaliacoes: MutableList<Int> = mutableListOf()
    override val media: Double
        get() = listaAvaliacoes.average()

    override fun recomendar(nota: Int) {
        listaAvaliacoes.add(nota)
    }

    override fun toString(): String {
        return "Game${id}: \nTitulo $titulo \nCapa: $capa  \nDescricao: $descricao\nPreço: ${preco.toBRL()} "
    }

}