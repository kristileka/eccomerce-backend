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

    @JsonIgnore
    @ManyToOne(cascade = [CascadeType.ALL], optional = true, fetch = FetchType.LAZY)
    var userOrder: UserOrder? = null

    @Column(name = "product_id")
    var productId: Long = 0

    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "orderItem")
    var orderItemsStock: MutableList<OrderItemStock> = mutableListOf()
}