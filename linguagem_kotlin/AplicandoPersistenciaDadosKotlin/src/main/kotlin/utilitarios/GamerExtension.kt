package utilitarios

import dados.entity.GamerEntity
import dados.entity.PlanosGamers
import modelo.planos.Plano
import modelo.planos.PlanoAvulso
import modelo.usuario.Gamer
import org.example.usuario.data.InfoGamerJson
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.persistence.EntityManager

private val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

fun InfoGamerJson.transformGame(): Gamer {
    return Gamer(
        this.nome,
        this.email,
        this.dataNascimento,
        this.usuario
    )
}

fun Gamer.toEntity(): GamerEntity {
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    return GamerEntity(
        nome = this.nome,
        email = this.email,
        dataNascimento = LocalDate.parse(this.dataNascimento, formatter),
        usuario = this.usuario,
        id = this.id
    )
}

fun GamerEntity.toModel(manager: EntityManager): Gamer{
    var plano: Plano = PlanoAvulso()

    val getPlano = kotlin.runCatching {
        manager.createQuery("FROM PlanosGamers WHERE gamer.id = :id", PlanosGamers::class.java)
            .setParameter("id", this.id).singleResult
    }

    getPlano.onSuccess {
        plano = it?.plano?.toModel() ?: PlanoAvulso()
    }

    return Gamer(
        nome = this.nome,
        email = this.email,
        usuario = this.usuario,
        dataNascimento = this.dataNascimento?.format(formatter).toString(),
        id = this.id,
        plano = plano
    )
}