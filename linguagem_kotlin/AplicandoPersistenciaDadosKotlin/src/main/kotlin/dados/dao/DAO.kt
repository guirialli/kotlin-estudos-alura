package dados.dao

import dados.entity.GamerEntity
import javax.persistence.EntityManager

abstract class DAO<TModel, TEntity>(protected val manager: EntityManager, protected val entityType: Class<TEntity>) {

    abstract fun toEntity(obj: TModel): TEntity
    abstract fun toModel(entity: TEntity): TModel

    open fun getLista(): List<TModel> {
        val query = manager.createQuery("FROM ${entityType.simpleName}", entityType)
        return query.resultList.map { entity -> toModel(entity) }
    }

    open fun getById(id: Any): TModel? {
        val entity = manager.find(entityType, id)
        if (entity != null)
            return toModel(entity)
        return null
    }

    open fun add(obj: TModel) {
        val entity = toEntity(obj)
        manager.transaction.begin()
        manager.persist(entity)
        manager.transaction.commit()
    }

    open fun delete(id: Any): Boolean {
        val entity = manager.find(entityType, id)
        if (entity != null) {
            manager.transaction.begin()
            manager.remove(entity)
            manager.transaction.commit()
            manager.detach(entity)
            return true
        }
        return false
    }
}