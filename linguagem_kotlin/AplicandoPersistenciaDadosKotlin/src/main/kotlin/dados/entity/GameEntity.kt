package dados.entity;

import javax.persistence.*;

@Entity
@Table(name = "jogos")
public class GameEntity(
    val titulo:String = "Titulo Jogo",
    val capa:String = "Capa Jogo" ,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id:Int=1,
    val preco:Double = 0.0 ,
    var descricao:String?=null
) {
}
