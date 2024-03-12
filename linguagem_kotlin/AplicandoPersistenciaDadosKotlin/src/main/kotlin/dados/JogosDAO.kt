package dados

import modelo.game.Game
import java.sql.SQLException

object JogosDAO {
    fun getJogos(): List<Game>{
        val listaJogos: MutableList<Game> = mutableListOf();
        val conexao = Banco.obterConexao()

        if(conexao !=  null){
            try {
                val statement = conexao.createStatement()
                val resultado = statement.executeQuery("SELECT * FROM jogos")
                while (resultado.next()){
                    val  id = resultado.getInt("id")
                    val capa = resultado.getString("capa")
                    val titulo =  resultado.getString("titulo")
                    val preco = resultado.getDouble("preco")
                    val descricao = resultado.getString("descricao")

                    listaJogos.add(Game(titulo= titulo, capa=capa, preco=preco, id=id, descricao = descricao))
                }
                statement.close()
                return listaJogos
            }finally {
                conexao.close()
            }
        }
        else
            throw SQLException("Algum erro certamento ocorreu!")
    }

    fun addJogo(jogo: Game){
        val conexao = Banco.obterConexao()
        val insert = "INSERT INTO jogos (titulo, capa, preco, descricao) VALUES (?, ?, ?, ?)"

        if(conexao != null){
            try {
                val statement = conexao.prepareStatement(insert)
                statement.setString(1, jogo.titulo)
                statement.setString(2, jogo.capa)
                statement.setDouble(3, jogo.preco)
                statement.setString(4, jogo.descricao)

                statement.executeUpdate()
                statement.close()

            }finally {
                conexao.close()
            }
        }
    }
}