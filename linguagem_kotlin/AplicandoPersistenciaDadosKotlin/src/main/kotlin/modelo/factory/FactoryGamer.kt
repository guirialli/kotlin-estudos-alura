package modelo.factory

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import modelo.usuario.Gamer
import org.example.usuario.data.InfoGamerJson
import org.example.utilitarios.tranformarEmIdade
import servico.ConsumoAPI
import utilitarios.transformGame
import java.util.Scanner

class FactoryGamer {

    companion object {
        fun createGameByAPI(): List<Gamer> {
            val endereco = "https://raw.githubusercontent.com/jeniblodev/arquivosJson/main/gamers.json"
            val json = ConsumoAPI.requestHttpJson(endereco)

            val gson = Gson()
            val meuGamerTipo = object : TypeToken<List<InfoGamerJson>>() {}.type
            val listaGameInfo: List<InfoGamerJson> = gson.fromJson(json, meuGamerTipo)
            val listaGameConvertida = listaGameInfo.map {
                it.transformGame()
            }
            return listaGameConvertida
        }

        fun createByCommandLine(sc : Scanner): Gamer {
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
                }while (!Gamer.regexData.matches(dataNascimento))

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