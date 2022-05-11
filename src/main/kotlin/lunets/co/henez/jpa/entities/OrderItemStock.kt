package lunets.co.henez.jpa.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.*

@Entity(name = "order_item_stock")
@JsonIgnoreProperties(ignoreUnknown = true)
class OrderItemStock {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "available_stock_id")
    var availableStockId: Long = 0

    @Column(name = "quantity")
    var quantity: Int = 0

    @JsonIgnore
    @ManyToOne(cascade = [CascadeType.ALL], optional = true, fetch = FetchType.LAZY)
    var orderItem: OrderItem? = null
}