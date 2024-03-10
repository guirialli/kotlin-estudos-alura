package servico

import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class ConsumoAPI {

    companion object{
        fun requestHttpJson(endereco: String): String? {
            val client: HttpClient = HttpClient.newHttpClient()
            val request = HttpRequest
                .newBuilder()
                .uri(URI.create(endereco))
                .build()
            val response = client.send(request, HttpResponse.BodyHandlers.ofString())

            return response.body()
        }
    }
}