package factory

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.example.usuario.Gamer
import org.example.usuario.data.InfoGamerJson
import servico.ConsumoAPI
import utilitarios.transformGame

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

    }
}