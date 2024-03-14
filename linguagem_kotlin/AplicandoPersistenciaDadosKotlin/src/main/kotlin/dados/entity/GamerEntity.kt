package dados.entity

import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "gamers")
class GamerEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Int = 1,
    var nome: String = "",
    var email: String = "",
    @Column(name = "data_nascimento", columnDefinition = "DATE")
    var dataNascimento : LocalDate? = null,
    val usuario: String? = null
) {}