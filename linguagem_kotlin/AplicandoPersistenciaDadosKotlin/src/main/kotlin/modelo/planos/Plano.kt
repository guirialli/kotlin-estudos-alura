package modelo.planos

import modelo.aluguel.Aluguel
import modelo.planos.servicos.PlanoServices

abstract class Plano(
    val tipo: String,
    val id: Int = 1
) {
    open fun calcularAluguel(aluguel: Aluguel): Double {
        return calcularValorDoJogo(aluguel)
    }

    override fun toString(): String {
        return "tipo: $tipo\n"
    }


    val calcularValorDoJogo:
                (aluguel: Aluguel) -> Double =
        { it.jogo.preco * it.periodo.dias  }

    open fun  jogosInclusosNoPlano(): Int{
        return 0
    }
}
