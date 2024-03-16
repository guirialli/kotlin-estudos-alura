package dados.dao

import dados.entity.PlanoEntity
import dados.entity.PlanosGamers
import modelo.planos.PlanoAssinatura
import utilitarios.toEntity
import utilitarios.toModel
import javax.persistence.EntityManager
import javax.transaction.Transactional

@Transactional
class PlanoDAO(manager: EntityManager) : DAO<PlanoAssinatura, PlanoEntity>(manager, PlanoEntity::class.java) {
    override fun toEntity(obj: PlanoAssinatura): PlanoEntity {
        return obj.toEntity()
    }

    override fun toModel(entity: PlanoEntity): PlanoAssinatura {
        return entity.toModel()
    }

    fun addPlanoGamer(gamerID: Int, planoID: Int) {
        val sql = """
        INSERT INTO planos_gamers (gamer_id, plano_id)
        VALUES (:gamerId, :planoId)
        """.trimIndent()

        val query = manager.createNativeQuery(sql)
        query.setParameter("gamerId", gamerID)
        query.setParameter("planoId", planoID)

        val addGameInPlano = kotlin.runCatching {
            manager.transaction.begin()
            query.executeUpdate()
            manager.transaction.commit()
        }

        addGameInPlano.onSuccess {
            println("Gamer adicionado ao plano!")
        }

        addGameInPlano.onFailure {
            manager.transaction.rollback()
            println("Erro ao adicionar gamer em plano, verifique se ele já tem um plano!:\n\nErro:${it.message}")
            it.printStackTrace()
        }
    }

    fun removerGamerPlano(gamerID: Int) {
        val getPlano = kotlin.runCatching {
            manager.createQuery("FROM PlanosGamers WHERE gamer.id = :id", PlanosGamers::class.java)
                .setParameter("id", gamerID).singleResult
        }

        getPlano.onSuccess {
            if (it != null){
                manager.transaction.begin()
                manager.remove(it)
                manager.transaction.commit()
            }
            else println("Gamer não possui plano!")
        }

        getPlano.onFailure {
            println("Erro: Gamer não possuí plano,ou, erro desconhecido: \n${it.message}")
        }
    }
    fun alterarGamerPlano(gamerID: Int, planoID: Int){
        removerGamerPlano(gamerID)
        addPlanoGamer(gamerID, planoID)
    }

}