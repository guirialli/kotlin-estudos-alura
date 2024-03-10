package modelo.recomendacao

interface Recomendavel {
    val media: Double
    fun recomendar(nota: Int)
}