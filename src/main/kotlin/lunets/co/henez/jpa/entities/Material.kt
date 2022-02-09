package lunets.co.henez.jpa.entities

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.*

@Entity(name = "material")
@JsonIgnoreProperties(ignoreUnknown = true)
class Material {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "name")
    var name: String? = null

}