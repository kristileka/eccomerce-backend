package lunets.co.henez.dto

data class ResponseUserOrderResponseDTO(
    var orderLocator: String?,
    var address: String?,
    var city: String?,
    var email: String?,
    var name: String?,
    var orderItems: List<ResponseOrderItemDTO>?,
    var paymentDetails: ResponsePaymentDetailsDTO?,
    var phoneNumber: String?,
    var totalPrice: Double?
)

data class ResponseOrderItemDTO(
    var orderStockItems: List<ResponseOrderStockItemDTO>?,
    var productId: Int?,
    var productPrice: Double?,
    var productName: String?,
    var mainImage: String?
)

data class ResponsePaymentDetailsDTO(
    var isPaymentCard: Boolean?,
    var isPaymentComplete: Boolean?
)

data class ResponseOrderStockItemDTO(
    var availabilityId: Int?,
    var quantity: Int?
)