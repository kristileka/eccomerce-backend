package lunets.co.henez.jpa.entities

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

    @OneToMany(cascade=[CascadeType.ALL], mappedBy="userOrder")
    var orderItems: List<OrderItem>? = null

    @NotBlank(message = "Address is mandatory")
    @Column(name = "address")
    var address: String? = null

    @NotBlank(message = "Phone is mandatory")
    @Column(name = "phone_number")
    var phoneNumber: String? = null

    @NotBlank(message = "Name is mandatory")
    @Column(name = "email")
    var email: String? = null

    @Column(name = "created_at")
    var createdAt: Timestamp? = Timestamp.valueOf(LocalDateTime.now())

    @Column(name = "updated_at")
    var updatedAt: Timestamp? = null

    @Column(name = "deleted_at")
    var deletedAt: Timestamp? = null

}
