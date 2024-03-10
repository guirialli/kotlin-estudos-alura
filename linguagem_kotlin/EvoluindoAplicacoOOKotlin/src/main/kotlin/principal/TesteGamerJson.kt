package principal

import factory.FactoryGamer

fun main(){
    val consumo = FactoryGamer.createGameByAPI()
    println(consumo)
}