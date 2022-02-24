package lunets.co.henez.jpa.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.sql.Timestamp
import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.NotBlank


@Entity(name = "available_stock")
@JsonIgnoreProperties(ignoreUnknown = true)
class AvailableStock {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "color")
    var color: String? = null

    @Column(name = "color_hex")
    var colorHex: String? = null

    @Column(name = "side")
    var side: String? = null

    @Column(name = "size")
    var size: String? = null

    @Column(name = "stock_quantity")
    var stockQuantity: String? = null

    @JsonIgnore
    @ManyToOne(cascade = [CascadeType.ALL], optional = true, fetch = FetchType.LAZY)
    var currentProduct: Product? = null

    @Column(name = "created_at")
    var createdAt: Timestamp? = Timestamp.valueOf(LocalDateTime.now())

    @Column(name = "updated_at")
    var updatedAt: Timestamp? = null

    @Column(name = "deleted_at")
    var deletedAt: Timestamp? = null
}