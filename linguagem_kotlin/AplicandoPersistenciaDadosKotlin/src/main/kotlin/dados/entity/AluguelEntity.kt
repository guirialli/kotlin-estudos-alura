package dados.entity

import java.time.LocalDate
import java.time.Period
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToMany
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "alugueis")
class AluguelEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Int = 1,
    @Column(name = "data_inicial", columnDefinition = "DATE") val dataInicial: LocalDate = LocalDate.now(),
    @Column(name = "data_final", columnDefinition = "DATE") val dataFinal: LocalDate = LocalDate.now().plusDays(7),
    @ManyToOne @JoinColumn(name = "jogo_id", referencedColumnName = "id") val game: GameEntity = GameEntity(),
    @ManyToOne @JoinColumn(name = "gamer_id", referencedColumnName = "id")val gamer: GamerEntity = GamerEntity()
){
    val dias = Period.between(dataInicial, dataFinal).days
}