package principal

import modelo.factory.FactoryGamer

fun main(){
    val consumo = FactoryGamer.createGameByAPI()
    println(consumo)
}