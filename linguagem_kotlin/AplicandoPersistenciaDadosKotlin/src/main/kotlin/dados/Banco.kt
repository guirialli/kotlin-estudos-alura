package dados

import io.github.cdimascio.dotenv.Dotenv
import modelo.game.Game
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory
import javax.persistence.Persistence

object Banco {

fun getEntityManager(): EntityManager{
    val factory: EntityManagerFactory = Persistence.createEntityManagerFactory("alugames")
    return  factory.createEntityManager()
}

}