package modelo.planos

import modelo.aluguel.Aluguel
import modelo.planos.servicos.PlanoServices
import javax.naming.directory.InvalidAttributesException

class PlanoAssinatura(
    tipo: String,
    val desconto: Double,
    val assinatura: Double,
    val jogosIncluidos: Int
) : Plano(tipo) {

    init {
        if (desconto < 0 || desconto > 1) {
            throw InvalidAttributesException("O desconto deve ser entregue em decimal ex: 0.34")
        } else if (assinatura < 0) {
            throw InvalidAttributesException("A assinatura não pode ter um valor negativo ")
        }
    }

    override fun calcularAluguel(aluguel: Aluguel): Double {
        val totalJogos = aluguel.gamer.jogosNoMes(aluguel.periodo.dataInicial).size

        return if (jogosIncluidos > totalJogos) 0.0
        else PlanoServices.calcularValorDoJogo(aluguel) - (PlanoServices.calcularValorDoJogo(aluguel) * desconto)
    }

    override fun toString(): String {
        return super.toString() +
                "desconto: $desconto\n" +
                "jogos incluídos: $jogosIncluidos\n" +
                "assinatura: $assinatura\n\n"
    }
}