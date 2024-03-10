package modelo.planos

import modelo.aluguel.Aluguel
import modelo.planos.servicos.PlanoServices

abstract class Plano(
    val tipo: String,
) {
   open fun calcularAluguel(aluguel: Aluguel): Double {
        return PlanoServices.calcularValorDoJogo(aluguel)
    }
    override fun toString(): String {
        return "tipo: $tipo\n"
    }
}
