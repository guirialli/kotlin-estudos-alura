package modelo.usuario

import modelo.aluguel.Aluguel
import modelo.aluguel.PeriodoAluguel
import modelo.game.Game
import modelo.planos.Plano
import modelo.planos.PlanoAvulso
import modelo.planos.servicos.recomendacao.Recomendavel
import java.time.LocalDate
import java.util.*
class Gamer(
    var nome: String,
    var email: String,
    var dataNascimento: String? = null,
    var usuario: String? = null
) : Recomendavel {
    var idIterno: String = UUID.randomUUID().toString()
        private set
    val gamesSearched: MutableList<Game?> = mutableListOf()
    val listDeAlugueis: MutableList<Aluguel?> = mutableListOf()
    var plano : Plano = PlanoAvulso()
    private var listaNotas = mutableListOf<Int>()
    override val media: Double
        get() = listaNotas.average()

    init {
        if (nome.isBlank())
            throw IllegalArgumentException("Nome não pode ser nulo, ou, em branco!")
        if(dataNascimento != null &&  !regexData.matches(dataNascimento.toString())){
           throw IllegalArgumentException("Data não corresponde ao padrão esperado")
        }
        this.email = validarEmail()
    }

    private fun validarEmail(): String {
        val regexEmail = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
        if (regexEmail.matches(email))
            return email
        else
            throw IllegalArgumentException("Email invalido!")
    }

    fun alugueisNoMes(dataDoMes: LocalDate): List<Aluguel?>{
        val mes = if(dataDoMes.monthValue <= 9) "0${dataDoMes.monthValue}" else dataDoMes.monthValue.toString()
        val ano = dataDoMes.year
        val dataInicio: LocalDate = LocalDate.parse("$ano-$mes-01")
        val dataFinal = dataInicio.plusMonths(1)
        val listaDeAlugueisFiltrada = listDeAlugueis.filter {
            (dataDoMes.isAfter(dataInicio) || dataDoMes.isEqual(dataInicio)) &&
                    (dataDoMes.isBefore(dataFinal))
        }
        return listaDeAlugueisFiltrada
    }

    fun jogosNoMes(dataDoMes: LocalDate): List<Game?>{
        val listaAlugueisFiltrada = alugueisNoMes(dataDoMes)
        return listaAlugueisFiltrada.map { it?.jogo }
    }

    fun alugarJogo(jogo: Game, diasDeAluguel: Long): Aluguel {
        val aluguel = Aluguel(
            jogo = jogo,
            gamer = this,
            periodo = PeriodoAluguel(
                dataInicial = LocalDate.now(),
                dataFinal = LocalDate.now().plusDays(diasDeAluguel)
            )
        )
        listDeAlugueis.add(aluguel)
        return aluguel
    }

    override fun recomendar(nota: Int) {
        listaNotas.add(nota)
    }

    override fun toString(): String {
        return "Gamer(nome = '${nome}', email='${email}'," +
                " dataNascimento=${dataNascimento}, ususario='${usuario}'idInterno='${idIterno} Avaliação: ${media}'\n"
    }

    companion object{
        val regexData = Regex("^(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/\\d{4}$")

    }

}