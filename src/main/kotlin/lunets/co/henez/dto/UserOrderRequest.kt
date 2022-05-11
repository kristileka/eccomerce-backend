package lunets.co.henez.dto

import org.springframework.boot.availability.ApplicationAvailability

data class UserOrderRequestDTO(
    var email: String,
    var name: String,
    var orderItems: List<OrderItemDTO> = listOf(),
    var address: String,
    var phoneNumber: String,
    var city: String,
    var paymentDetails: PaymentDetails
)

data class PaymentDetails(
    var isPaymentCard: Boolean,
    var nonce: String? = ""
)

data class OrderItemDTO(
    var productId: Long,
    var orderStockItems: List<OrderItemStockDTO> = listOf()
)

data class OrderItemStockDTO(
    var availabilityId: Long,
    var quantity: Int
)