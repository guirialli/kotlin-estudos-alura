package org.example.principal

import org.example.usuario.Gamer

fun main() {
    val gamer1 = Gamer(nome = "Guilherme Rialli Oliveira", email = "gu@gmail.com")
    gamer1.let {
        it.dataNascimento = "10/07/2002"
        it.usuario = "guirialli"
    }

    val gamer2 =
        Gamer("Ash Keshion", "ash.keshion@example.com", "09/03/2004", "ashkeshiom")

    println(gamer1)
    println(gamer2)
    println(gamer2.idIterno)
}