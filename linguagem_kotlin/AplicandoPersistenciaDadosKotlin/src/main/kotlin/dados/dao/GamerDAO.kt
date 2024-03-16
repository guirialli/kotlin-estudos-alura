package dados.dao

import dados.entity.GamerEntity
import modelo.usuario.Gamer
import utilitarios.toEntity
import utilitarios.toModel
import java.time.format.DateTimeFormatter
import javax.persistence.EntityManager

class GamerDAO(manager: EntityManager) : DAO<Gamer, GamerEntity>(manager, entityType = GamerEntity::class.java) {
    override fun toEntity(obj: Gamer): GamerEntity {
        return obj.toEntity()
    }

    override fun toModel(entity: GamerEntity): Gamer {
        return entity.toModel(manager)
    }
}