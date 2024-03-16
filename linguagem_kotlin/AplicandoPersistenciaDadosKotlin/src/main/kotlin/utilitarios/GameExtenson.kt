package utilitarios

import dados.entity.GameEntity
import modelo.game.Game

fun GameEntity.toModel(): Game{
    return Game(
        titulo = this.titulo,
        capa = this.capa,
        descricao = this.descricao,
        id = this.id,
        preco = this.preco
    )
}

fun Game.toEntity(): GameEntity{
    return GameEntity(titulo = this.titulo, capa = this.capa, descricao = this.descricao, preco = this.preco)
}