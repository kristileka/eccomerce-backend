package lunets.co.henez.jpa.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.sql.Timestamp
import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.NotBlank


@Entity(name = "user_order")
@JsonIgnoreProperties(ignoreUnknown = true)
class UserOrder {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @NotBlank(message = "Name is mandatory")
    @Column(name = "name")
    var name: String? = null

    @Column(name = "order_locator")
    var orderLocator: String? = null

    @NotBlank(message = "Address is mandatory")
    @Column(name = "address")
    var address: String? = null

    @Column(name = "status")
    var status: String? = null

    @Column(name = "note")
    var note: String? = null

    @NotBlank(message = "Address is mandatory")
    @Column(name = "city")
    var city: String? = null

    @Column(name = "total_price")
    var totalPrice: Double? = null

    @NotBlank(message = "Phone is mandatory")
    @Column(name = "phone_number")
    var phoneNumber: String? = null

    @Column(name = "email")
    var email: String? = null

    @Column(name = "is_card_payment")
    var isCardPayment: Boolean? = false

    @Column(name = "is_payment_complete")
    @JsonIgnore
    var isPaymentComplete: Boolean? = false

    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "userOrder")
    var orderItems: MutableList<OrderItem> = mutableListOf()

    @Column(name = "created_at")
    @JsonIgnore
    var createdAt: Timestamp? = Timestamp.valueOf(LocalDateTime.now())

    @Column(name = "updated_at")
    @JsonIgnore
    var updatedAt: Timestamp? = null

    @Column(name = "deleted_at")
    @JsonIgnore
    var deletedAt: Timestamp? = null

}
