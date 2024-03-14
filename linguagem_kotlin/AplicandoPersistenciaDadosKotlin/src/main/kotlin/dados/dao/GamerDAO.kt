package dados.dao

import dados.entity.GamerEntity
import modelo.usuario.Gamer
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.persistence.EntityManager
import javax.swing.text.DateFormatter

class GamerDAO(manager: EntityManager) : DAO<Gamer, GamerEntity>(manager, entityType = GamerEntity::class.java) {
    private val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    override fun toEntity(obj: Gamer): GamerEntity {
        return GamerEntity(
            nome = obj.nome,
            email = obj.email,
            usuario = obj.usuario,
            dataNascimento = LocalDate.parse(obj.dataNascimento, formatter)
        )
    }

    override fun toModel(entity: GamerEntity): Gamer {

       return  Gamer(
           nome = entity.nome,
           email = entity.email,
           usuario = entity.usuario,
           dataNascimento = entity.dataNascimento?.format(formatter).toString()
       )
    }

}