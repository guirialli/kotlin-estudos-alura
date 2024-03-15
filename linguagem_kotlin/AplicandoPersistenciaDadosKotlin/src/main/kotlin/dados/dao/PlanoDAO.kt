package dados.dao

import dados.entity.PlanoEntity
import dados.entity.PlanosGamers
import modelo.planos.PlanoAssinatura
import utilitarios.toEntity
import utilitarios.toModel
import java.sql.SQLException
import javax.persistence.EntityManager

class PlanoDAO(manager: EntityManager) : DAO<PlanoAssinatura, PlanoEntity>(manager, PlanoEntity::class.java) {
    override fun toEntity(obj: PlanoAssinatura): PlanoEntity {
        return obj.toEntity()
    }
    override fun toModel(entity: PlanoEntity): PlanoAssinatura {
        return entity.toModel()
    }

    fun addPlanoGamer(gamerID: Int, planoID: Int) {
        val gamer = GamerDAO(manager).getById(gamerID)
        val plano = PlanoDAO(manager).getById(planoID)

        if(gamer == null || plano == null){
            throw RuntimeException("Gamer, ou, plano invalidos")
        }
        try{
            manager.transaction.begin()
            manager.persist(PlanosGamers(gamer = gamer.toEntity(), plano = plano.toEntity()))
            manager.transaction.commit()
        }catch (e: SQLException){
         println("Gamer já possuí um plano!")
        }
        catch (e: Exception){
            manager.transaction.rollback()
            println("Erro desconhecido: $e")
        }
    }
}