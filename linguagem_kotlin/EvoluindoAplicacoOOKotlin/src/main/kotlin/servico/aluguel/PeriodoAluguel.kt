package servico.aluguel

import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

class PeriodoAluguel (
    val dataInicial: LocalDate,
    val dataFinal: LocalDate,
    val dias: Int = Period.between(dataInicial, dataFinal).days
) {

    private val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    override fun toString(): String {
        return "Data do Aluguel: ${dataInicial.format(formatter)}\n" +
                "Data de devolução: ${dataFinal.format(formatter)}\n"
    }
}