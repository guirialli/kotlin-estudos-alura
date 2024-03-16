package dados.dao

import dados.entity.GamerEntity
import dados.entity.PlanosGamers
import modelo.planos.Plano
import modelo.planos.PlanoAvulso
import modelo.usuario.Gamer
import utilitarios.toEntity
import utilitarios.toModel
import java.time.format.DateTimeFormatter
import javax.persistence.EntityManager

class GamerDAO(manager: EntityManager) : DAO<Gamer, GamerEntity>(manager, entityType = GamerEntity::class.java) {
    private val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    override fun toEntity(obj: Gamer): GamerEntity {
        return obj.toEntity()
    }

    override fun toModel(entity: GamerEntity): Gamer {
        var plano: Plano = PlanoAvulso();

        val getPlano = kotlin.runCatching {
            manager.createQuery("FROM PlanosGamers WHERE gamer.id = :id", PlanosGamers::class.java)
                .setParameter("id", entity.id).singleResult
        }

        getPlano.onSuccess {
            plano = it?.plano?.toModel() ?: PlanoAvulso()
        }

        return Gamer(
            nome = entity.nome,
            email = entity.email,
            usuario = entity.usuario,
            dataNascimento = entity.dataNascimento?.format(formatter).toString(),
            id = entity.id,
            plano = plano
        )
    }
}