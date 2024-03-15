package dados.entity

import javax.persistence.*

@Entity
@Table(name = "planos_gamers")
class PlanosGamers(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Int = 1,
    @ManyToOne
    @JoinColumn(name = "plano_id", referencedColumnName = "id")
    val plano: PlanoEntity = PlanoEntity(),
    @ManyToOne
    @JoinColumn(name = "gamer_id", referencedColumnName = "id")
    val gamer: GamerEntity = GamerEntity()
) {}