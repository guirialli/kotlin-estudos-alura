package utilitarios

import dados.entity.GamerEntity
import modelo.usuario.Gamer
import org.example.usuario.data.InfoGamerJson
import java.time.LocalDate
import java.time.format.DateTimeFormatter

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