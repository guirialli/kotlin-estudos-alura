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
    fun obterConexao(): Connection?{
        val dotenv = Dotenv.load()

        return  try{
            DriverManager.getConnection(
                dotenv.get("DB_CONNECTION"),
                dotenv.get("DB_USER"), dotenv.get("DB_PASSWORD")
                )
        }catch (e: SQLException){
            e.printStackTrace()
            null
        }
    }
fun getEntityManager(): EntityManager{
    val factory: EntityManagerFactory = Persistence.createEntityManagerFactory("alugames")
    return  factory.createEntityManager()
}

}