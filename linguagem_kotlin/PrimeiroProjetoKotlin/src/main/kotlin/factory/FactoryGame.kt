package org.example.factory

import com.google.gson.Gson
import org.example.game.Game
import org.example.game.InfoGame
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class FactoryGame {

    companion object{
    fun createByApiShark(idGame: Int, descricao: String?): Game {
        val endereco = "https://www.cheapshark.com/api/1.0/games?id=${idGame}"
        val client: HttpClient = HttpClient.newHttpClient()
        val request = HttpRequest.newBuilder()
            .uri(URI.create(endereco))
            .build()
        val response = client.send(request, HttpResponse.BodyHandlers.ofString())

        // convertendo os dados recebidos para JSON
        val gson = Gson()
        val json = response.body()

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