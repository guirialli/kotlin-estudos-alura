package dados.dao

import dados.entity.AluguelEntity
import modelo.aluguel.Aluguel
import modelo.aluguel.PeriodoAluguel
import utilitarios.toEntity
import utilitarios.toModel
import javax.persistence.EntityManager

class AluguelDAO(manager: EntityManager): DAO<Aluguel, AluguelEntity>(manager, entityType = AluguelEntity::class.java) {
    override fun toEntity(obj: Aluguel): AluguelEntity {
      return AluguelEntity(
          dataInicial = obj.periodo.dataInicial,
          dataFinal = obj.periodo.dataFinal,
          gamer = obj.gamer.toEntity(),
          game = obj.jogo.toEntity()
      )
    }

    override fun toModel(entity: AluguelEntity): Aluguel {
        return  Aluguel(
            periodo = PeriodoAluguel(dataInicial = entity.dataInicial, dataFinal = entity.dataFinal),
            gamer = entity.gamer.toModel(manager),
            jogo = entity.game.toModel(),
        )
    }

    override fun add(obj: Aluguel) {
        val sql = """
        INSERT INTO alugueis (gamer_id, jogo_id, dias, data_inicial, data_final)
        VALUES (:gamerId, :jogoId, :dias, :data_inicial, :data_final)
        """.trimIndent()

        val query = manager.createNativeQuery(sql)
        query.setParameter("gamerId", obj.gamer.id)
        query.setParameter("jogoId", obj.jogo.id)
        query.setParameter("dias", obj.periodo.dias)
        query.setParameter("data_inicial", obj.periodo.dataInicial)
        query.setParameter("data_final", obj.periodo.dataFinal)

        val addAluguel = kotlin.runCatching {
            manager.transaction.begin()
            query.executeUpdate()
            manager.transaction.commit()
        }

        addAluguel.onSuccess {
            println("Gamer adicionado ao plano!")
        }

        addAluguel.onFailure {
            manager.transaction.rollback()
            println("Erro ao adicionar gamer em plano, verifique se ele já tem um plano!:\n\nErro:${it.message}")
            it.printStackTrace()
        }

    }
}