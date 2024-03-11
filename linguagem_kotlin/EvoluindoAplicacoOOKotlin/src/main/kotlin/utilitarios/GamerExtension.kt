package utilitarios

import modelo.usuario.Gamer
import org.example.usuario.data.InfoGamerJson

fun InfoGamerJson.transformGame() : Gamer {
    return Gamer(
        this.nome,
        this.email,
        this.dataNascimento,
        this.usuario
    )
}