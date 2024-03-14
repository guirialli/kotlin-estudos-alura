package principal

import dados.dao.GamerDAO
import dados.entity.GamerEntity
import modelo.factory.FactoryGamer

fun main(){
    val consumo = FactoryGamer.createGameByAPI()
    println(consumo)
}