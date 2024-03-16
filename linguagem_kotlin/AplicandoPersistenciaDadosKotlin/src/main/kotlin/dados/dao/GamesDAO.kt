package dados.dao

import dados.entity.GameEntity
import modelo.game.Game
import utilitarios.toEntity
import utilitarios.toModel
import javax.persistence.EntityManager

class GamesDAO(manager: EntityManager) : DAO<Game, GameEntity>(manager, entityType = GameEntity::class.java) {
    override fun toEntity(obj: Game): GameEntity {
        return obj.toEntity()
    }

    override fun toModel(entity: GameEntity): Game {
        return entity.toModel()
    }
}