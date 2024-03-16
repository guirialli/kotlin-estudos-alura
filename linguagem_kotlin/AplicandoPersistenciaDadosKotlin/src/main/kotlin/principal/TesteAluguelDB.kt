package principal

import dados.Banco
import dados.dao.AluguelDAO
import dados.dao.GamerDAO
import dados.dao.GamesDAO
import dados.entity.AluguelEntity
import modelo.aluguel.Aluguel
import modelo.aluguel.PeriodoAluguel
import modelo.game.Game
import utilitarios.toEntity
import java.time.LocalDate

fun main(){
    val manager = Banco.getEntityManager()

    try{
        val aluguelDAO = AluguelDAO(manager)
        val game = GamesDAO(manager).getById(2)
        val gamer = GamerDAO(manager).getById(1)

        if(game != null && gamer != null){
            val aluguel = Aluguel(
                gamer= gamer,
                jogo = game,
                periodo = PeriodoAluguel(
                    dataInicial = LocalDate.now(),
                    dataFinal = LocalDate.now().plusDays(7)
                )
            )
            aluguelDAO.add(aluguel)
        }

        println(aluguelDAO.getLista())

    }finally {
        manager.close()
    }
}