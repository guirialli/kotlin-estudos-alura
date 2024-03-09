package org.example.usuario

import org.example.game.Game
import org.example.utilitarios.tranformarEmIdade
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

    override fun toString(): String {
        return "Gamer(nome = '${nome}', email='${email}', dataNascimento=${dataNascimento}, ususario='${usuario}'idInterno='${idIterno}'"
    }

    companion object{
        val regexData = Regex("^(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/\\d{4}$")

        fun creatGame(sc: Scanner): Gamer{
            val gamer : Gamer
            println("Bem vindo, vamos começar com o seu cadastro:")
            println("Informe seu nome completo: ")
            val nome = sc.nextLine()

            println("informe seu email: ")
            val email = sc.nextLine()

            println("Você deseja fazer o cadastro completo?")
            val op = sc.nextLine()
            if(op.equals("s", true) || op.equals("sim", true)){
                var dataNascimento: String
                do {
                    println("Informe a sua data de nascimento(dd/MM/yyyy)")
                    dataNascimento = sc.nextLine()
                }while (!regexData.matches(dataNascimento))

                println("Informe sue nome de usuários")
                val usuario = sc.nextLine()

                gamer =  Gamer(nome, email, dataNascimento, usuario)
            }
            else
                gamer = Gamer(nome, email)

            println(gamer)
            println("idade: ${gamer.dataNascimento?.tranformarEmIdade()}")
            return  gamer
        }
    }

}