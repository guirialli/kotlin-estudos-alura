package dados.entity

import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "planos")
class PlanoEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Int = 1,
    val tipo: String = "avulso",
    @Column(name = "MENSALIDADE", nullable = false, columnDefinition = "NUMERIC(10,2)")
    val mensalidade: BigDecimal = BigDecimal.ZERO,
    @Column(name = "JOGOS_INCLUIDOS", nullable = false, columnDefinition = "INT")
    val jogosIncluidos: Int = 1,
    @Column(name = "PERCENTUAL_DESCONTO", nullable = false, columnDefinition = "NUMERIC(1,2)")
    val percentualDesconto: BigDecimal = BigDecimal.ZERO,

) {

}