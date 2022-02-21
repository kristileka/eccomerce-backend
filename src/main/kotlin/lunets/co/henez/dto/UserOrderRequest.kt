package lunets.co.henez.dto

data class UserOrderRequestDTO(
    var email: String,
    var name: String,
    var orderItems: List<OrderItemDTO>,
    var address: String,
    var phoneNumber: String,
)

data class PaymentDetails(
    var nonce: String
)

data class OrderItemDTO(
    var productId: Long,
    var quantity: Int
)