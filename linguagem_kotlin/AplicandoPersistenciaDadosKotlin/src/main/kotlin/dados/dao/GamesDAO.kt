package dados.dao

import dados.entity.GameEntity
import modelo.game.Game
import javax.persistence.EntityManager

class GamesDAO(manager: EntityManager) : DAO<Game, GameEntity>(manager, entityType = GameEntity::class.java) {
    override fun toEntity(obj: Game): GameEntity {
        return GameEntity(titulo = obj.titulo, capa = obj.capa, descricao = obj.descricao, preco = obj.preco)
    }

    override fun toModel(entity: GameEntity): Game {
        return Game(
            titulo = entity.titulo,
            capa = entity.capa,
            descricao = entity.descricao,
            id = entity.id,
            preco = entity.preco
        )
    }
}