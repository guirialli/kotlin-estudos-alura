package org.example.usuario

import game.Game
import org.example.utilitarios.tranformarEmIdade
import servico.aluguel.Aluguel
import servico.aluguel.PeriodoAluguel
import java.time.LocalDate
import java.util.Scanner
import java.util.UUID

class Gamer(
    var nome: String,
    var email: String,
    var dataNascimento: String? = null,
    var usuario: String? = null
) {
    var idIterno: String = UUID.randomUUID().toString()
        private set
    val gamesSearched: MutableList<Game?> = mutableListOf()
    val listDeAlugueis: MutableList<Aluguel?> = mutableListOf()

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

    fun alugarJogo(jogo:Game, diasDeAluguel: Long): Aluguel{
        val aluguel : Aluguel = Aluguel(
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

    override fun toString(): String {
        return "Gamer(nome = '${nome}', email='${email}', dataNascimento=${dataNascimento}, ususario='${usuario}'idInterno='${idIterno}'\n"
    }

    companion object{
        val regexData = Regex("^(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/\\d{4}$")

    }

}