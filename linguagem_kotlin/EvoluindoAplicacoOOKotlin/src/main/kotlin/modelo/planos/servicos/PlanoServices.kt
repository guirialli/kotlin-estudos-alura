package modelo.planos.servicos

import modelo.aluguel.Aluguel

class PlanoServices {

    companion object{
        val calcularValorDoJogo:
                    (aluguel: Aluguel) -> Double = { it.jogo.preco * it.periodo.dias }
    }
}