package factory

import com.google.gson.Gson
import org.example.game.Game
import org.example.game.InfoGame
import servico.ConsumoAPI
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class FactoryGame {

    companion object {
        fun createByApiShark(idGame: Int, descricao: String?): Game {
            val endereco = "https://www.cheapshark.com/api/1.0/games?id=${idGame}"
            val json = ConsumoAPI.requestHttpJson(endereco)

            // convertendo os dados recebidos para JSON
            val gson = Gson()
            val meuInfoJogo = gson.fromJson(json, InfoGame::class.java)

            // desestruturando o info
            val info = meuInfoJogo.info
            val meuJogo = if (descricao != null) {
                Game(titulo = info.title, capa = info.thumb, descricao = descricao)
            } else
                Game(titulo = info.title, capa = info.thumb)
            return meuJogo
        }
    }
}