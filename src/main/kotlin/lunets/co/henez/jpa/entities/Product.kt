package lunets.co.henez.jpa.entities

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.sql.Timestamp
import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.NotBlank


@Entity(name = "product")
@JsonIgnoreProperties(ignoreUnknown = true)
class Product {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @NotBlank(message = "Name is mandatory")
    @Column(name = "name")
    var name: String? = null

    @ElementCollection
    @Column(name = "image")
    var images: List<String> = listOf()

    @NotBlank(message = "Code is mandatory")
    @Column(name = "code", unique = true)
    var code: String? = null

    @Column(name = "price")
    var price: Double? = 0.0


    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "currentProduct")
    var availableStocks: MutableList<AvailableStock> = mutableListOf()


    @NotBlank(message = "Description is mandatory")
    @Column(name = "description")
    var description: String? = null

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "product_materials",
        joinColumns = [JoinColumn(name = "product_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "material_id", referencedColumnName = "id")]
    )
    var materials: List<Material> = mutableListOf()

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "product_category",
        joinColumns = [JoinColumn(name = "product_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "category_id", referencedColumnName = "id")]
    )
    var categories: List<Category> = mutableListOf()


    @Column(name = "new_product")
    var newProduct: Boolean? = false

    @Column(name = "created_at")
    var createdAt: Timestamp? = Timestamp.valueOf(LocalDateTime.now())

    @Column(name = "updated_at")
    var updatedAt: Timestamp? = null

    @Column(name = "deleted_at")
    var deletedAt: Timestamp? = null
}
