package lunets.co.henez.jpa.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.sun.istack.NotNull
import javax.persistence.*


@Entity(name = "order_item")
@JsonIgnoreProperties(ignoreUnknown = true)
class OrderItem {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "quantity")
    var quantity = 0


    @JsonIgnore
    @ManyToOne(cascade = [CascadeType.ALL], optional = true, fetch = FetchType.LAZY)
    var userOrder: UserOrder? = null

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    var product: Product? = null
}