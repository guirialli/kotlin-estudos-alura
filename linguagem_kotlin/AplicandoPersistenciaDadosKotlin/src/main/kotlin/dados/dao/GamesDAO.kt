package dados.dao

import dados.entity.GameEntity
import modelo.game.Game
import javax.persistence.EntityManager

class GamesDAO(val manager: EntityManager) {
    fun getJogos(): List<Game> {

        val query = manager.createQuery("FROM GameEntity", GameEntity::class.java)
        return query.resultList.map {
            Game(
                titulo = it.titulo, capa = it.capa, descricao = it.descricao, id = it.id, preco = it.preco
            )
        }
    }

    fun addJogo(jogo: Game) {
        val jogoEntity =
            GameEntity(titulo = jogo.titulo, capa = jogo.capa, descricao = jogo.descricao, preco = jogo.preco)
        manager.transaction.begin()
        manager.persist(jogoEntity)
        manager.transaction.commit()
    }

}