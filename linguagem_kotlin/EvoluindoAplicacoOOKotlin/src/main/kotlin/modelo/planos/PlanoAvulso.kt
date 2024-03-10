package modelo.planos

import modelo.aluguel.Aluguel
import modelo.planos.servicos.PlanoServices

class PlanoAvulso : Plano(
    tipo = "Bronze"
){

    override fun calcularAluguel(aluguel: Aluguel): Double {
        return PlanoServices.calcularValorDoJogo(aluguel)
    }


}