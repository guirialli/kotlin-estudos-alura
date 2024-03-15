package org.example.principal

import dados.Banco
import dados.dao.GamerDAO
import modelo.usuario.Gamer

fun main() {
    val gamer1 = Gamer(nome = "Guilherme Rialli Oliveira", email = "gu@gmail.com")
    gamer1.let {
        it.dataNascimento = "10/07/2002"
        it.usuario = "guirialli"
    }

    val gamer2 =
        Gamer("Ash Keshion", "ash.keshion@example.com", "09/03/2004", "ashkeshiom")


    val manger = Banco.getEntityManager()
    val gamerDAO = GamerDAO(manger)
    try{
        // gamerDAO.add(gamer2)
        val gamerDB = gamerDAO.getById(34)
        println(gamerDB)
        println(gamerDB?.plano)
    }finally {
        manger.close()
    }
}