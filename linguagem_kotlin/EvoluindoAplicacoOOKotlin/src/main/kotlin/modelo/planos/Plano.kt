package modelo.planos

import modelo.aluguel.Aluguel
import modelo.planos.servicos.PlanoServices

abstract class Plano(
    val tipo: String,
) {
    open fun calcularAluguel(aluguel: Aluguel): Double {
        return calcularValorDoJogo(aluguel)
    }

    override fun toString(): String {
        return "tipo: $tipo\n"
    }

    private fun reputacaoPorPlano(media: Double): Double{
        if(tipo.equals("bronze", true) && media > 8)
            return 0.25
        if(tipo.equals("prata", true) && media > 4.5){
            return 0.30
        }
        else if(tipo.equals("ouro", true) && media > 4)
            return 0.32
        else if(tipo.equals("diamante", true) && media > 3.5)
            return 0.35
        return 0.0
    }

    val calcularValorDoJogo:
                (aluguel: Aluguel) -> Double =
        { it.jogo.preco * it.periodo.dias - ((it.jogo.preco * it.periodo.dias) * reputacaoPorPlano(it.gamer.media)) }
}
