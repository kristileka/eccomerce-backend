package lunets.co.henez.services.impl

import lunets.co.henez.dto.*
import lunets.co.henez.jpa.entities.OrderItem
import lunets.co.henez.jpa.entities.OrderItemStock
import lunets.co.henez.jpa.entities.Product
import lunets.co.henez.jpa.entities.UserOrder
import lunets.co.henez.jpa.repositories.ProductRepository
import lunets.co.henez.jpa.repositories.UserOrderRepository
import lunets.co.henez.services.PaymentService
import lunets.co.henez.services.UserOrderService
import lunets.co.henez.utis.RecordLocatorGenerator.Companion.generateOrderLocator
import org.springframework.stereotype.Service

@Service
class UserOrderServiceImpl(
    private val userOrderRepository: UserOrderRepository,
    private val productRepository: ProductRepository,
    private val paymentService: PaymentService
) : UserOrderService {
    override fun createOrder(userOrderRequestDTO: UserOrderRequestDTO): ResponseUserOrderResponseDTO {
        val products = productRepository.findByIdIn(userOrderRequestDTO.orderItems.map {
            it.productId
        })

        if (userOrderRequestDTO.orderItems.any { orderItem -> orderItem.productId !in products.map { product -> product.id } }) {
            throw RuntimeException("Some of the Product Ids are not found")
        }
        userOrderRequestDTO.orderItems.forEach { orderItemDTO ->
            val currentProduct = products.first { it.id == orderItemDTO.productId }
            if (orderItemDTO.orderStockItems.any { stock ->
                    stock.availabilityId !in currentProduct.availableStocks.filter { it.stockQuantity >= stock.quantity }
                        .map { it.id }
                }) {
                throw RuntimeException("Product is not available or not found. ")

            }
        }
        val totalPrice = getTotalPriceByUserOrder(userOrderRequestDTO, products)

        val userOrder = UserOrder().apply {
            this.name = userOrderRequestDTO.name
            this.address = userOrderRequestDTO.address
            this.phoneNumber = userOrderRequestDTO.phoneNumber
            this.email = userOrderRequestDTO.email
            this.city = userOrderRequestDTO.city
            this.totalPrice = totalPrice
            this.orderLocator = generateOrderLocator()
        }
        if (userOrderRequestDTO.paymentDetails.isPaymentCard) {
            userOrder.isCardPayment = true
            userOrder.isPaymentComplete =
                paymentService.commitPayment(userOrderRequestDTO.paymentDetails.nonce ?: "", totalPrice)
        }

        userOrderRepository.save(userOrder)
        userOrder.orderItems = userOrderRequestDTO.orderItems.map { orderItemDTO ->
            val orderItem = OrderItem().apply {
                this.userOrder = userOrder
                this.productId = orderItemDTO.productId
                this.price = products.first { it.id == orderItemDTO.productId }.price
            }
            orderItemDTO.orderStockItems.map {
                val orderStockItems = orderItemDTO.orderStockItems.map { orderItemStockDTO ->
                    OrderItemStock().apply {
                        this.orderItem = orderItem
                        this.availableStockId = orderItemStockDTO.availabilityId
                        this.quantity = orderItemStockDTO.quantity
                    }

                }.toMutableList()
                orderItem.orderItemsStock = orderStockItems
            }
            orderItem
        }.toMutableList()
        return mapOrderResponse(userOrderRepository.save(userOrder), products)
    }

    private fun mapOrderResponse(order: UserOrder, products: List<Product>): ResponseUserOrderResponseDTO {
        return ResponseUserOrderResponseDTO(
            name = order.name,
            city = order.city,
            email = order.email,
            phoneNumber = order.phoneNumber,
            address = order.address,
            paymentDetails = mapPaymentDetails(order),
            orderItems = mapOrderItems(order.orderItems, products),
            totalPrice = order.totalPrice,
            orderLocator = order.orderLocator
        )

    }

    private fun mapOrderItems(
        orderItems: MutableList<OrderItem>, products: List<Product>
    ): List<ResponseOrderItemDTO> {
        return orderItems.map { orderItem ->
            val currentProduct = products.first { it.id == orderItem.productId }
            ResponseOrderItemDTO(
                productId = orderItem.productId.toInt(),
                mainImage = currentProduct.images.firstOrNull(),
                productPrice = currentProduct.price,
                productName = currentProduct.name,
                orderStockItems = mapOrderItemStock(orderItem, currentProduct)
            )
        }

    }

    private fun mapOrderItemStock(orderItem: OrderItem, product: Product): List<ResponseOrderStockItemDTO>? {
        return orderItem.orderItemsStock.map { stockItem ->
            val currentStock = product.availableStocks.first { it.id == stockItem.availableStockId }
            ResponseOrderStockItemDTO(
                availabilityId = stockItem.availableStockId.toInt(),
                quantity = stockItem.quantity,
                name = currentStock.name
            )
        }

    }

    private fun mapPaymentDetails(order: UserOrder): ResponsePaymentDetailsDTO {
        return ResponsePaymentDetailsDTO(order.isCardPayment, order.isPaymentComplete)
    }

    private fun getTotalPriceByUserOrder(
        userOrderRequestDTO: UserOrderRequestDTO, listProducts: List<Product>
    ): Double {
        var totalPrice = 0.0
        userOrderRequestDTO.orderItems.map { orderItem ->
            val countPerItem = orderItem.orderStockItems.map { it.quantity }.sum().toDouble()
            val product = listProducts.firstOrNull {
                it.id == orderItem.productId
            }
            totalPrice += (countPerItem * (product?.price ?: 0.0))
        }
        return totalPrice
    }

    override fun getOrders(): List<UserOrder> {
        return userOrderRepository.findAll()
    }

    override fun getOrderByLocator(orderLocator: String): ResponseUserOrderResponseDTO {
        val order = userOrderRepository.findByOrderLocator(orderLocator)
        if (order.isPresent) {
            val products = productRepository.findByIdIn(order.get().orderItems.map {
                it.id!!.toLong()
            })
            return mapOrderResponse(order.get(), products)
        }
        throw RuntimeException("Order not found")
    }
}