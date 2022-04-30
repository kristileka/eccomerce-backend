package lunets.co.henez.jpa.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.sql.Timestamp
import java.time.LocalDateTime
import javax.persistence.*


@Entity(name = "category")
@JsonIgnoreProperties(ignoreUnknown = true)
class Category {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "name", unique = true)
    var name: String? = null

    @Column(name = "icon")
    var icon: String? = null

    @Column(name = "new_category")
    var newCategory: Boolean? = false

    @Column(name = "created_at")
    @JsonIgnore
    var createdAt: Timestamp? = Timestamp.valueOf(LocalDateTime.now())

    @JsonIgnore
    @Column(name = "updated_at")
    var updatedAt: Timestamp? = null

    @JsonIgnore
    @Column(name = "deleted_at")
    var deletedAt: Timestamp? = null

}