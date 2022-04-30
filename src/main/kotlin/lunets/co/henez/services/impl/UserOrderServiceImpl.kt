package lunets.co.henez.services.impl

import lunets.co.henez.dto.UserOrderRequestDTO
import lunets.co.henez.jpa.entities.OrderItem
import lunets.co.henez.jpa.entities.OrderItemStock
import lunets.co.henez.jpa.entities.Product
import lunets.co.henez.jpa.entities.UserOrder
import lunets.co.henez.jpa.repositories.ProductRepository
import lunets.co.henez.jpa.repositories.UserOrderRepository
import lunets.co.henez.services.PaymentService
import lunets.co.henez.services.UserOrderService
import org.springframework.stereotype.Service

@Service
class UserOrderServiceImpl(
    private val userOrderRepository: UserOrderRepository,
    private val productRepository: ProductRepository,
    private val paymentService: PaymentService
) : UserOrderService {
    override fun createOrder(userOrderRequestDTO: UserOrderRequestDTO): UserOrder {
        val products = productRepository.findByIdIn(userOrderRequestDTO.orderItems.map {
            it.productId
        })

        if (userOrderRequestDTO.orderItems.any { orderItem -> orderItem.productId !in products.map { product -> product.id } }) {
            throw RuntimeException("Some of the Product Ids are not found")
        }
        var userOrder = UserOrder().apply {
            this.name = userOrderRequestDTO.name
            this.address = userOrderRequestDTO.address
            this.phoneNumber = userOrderRequestDTO.phoneNumber
            this.email = userOrderRequestDTO.email
            this.city = userOrderRequestDTO.city
        }
        if (userOrderRequestDTO.paymentDetails.isPaymentCard) {
            userOrder.isCardPayment = true
            val totalPrice = getTotalPriceByUserOrder(userOrderRequestDTO, products)
            userOrder.isPaymentComplete =
                paymentService.commitPayment(userOrderRequestDTO.paymentDetails.nonce ?: "", totalPrice)


        }

        userOrderRepository.save(userOrder)

        userOrder.orderItems = userOrderRequestDTO.orderItems.map { orderItemDTO ->
            val orderItem = OrderItem().apply {
                this.userOrder = userOrder
                this.productId = orderItemDTO.productId
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
        return userOrderRepository.save(userOrder)
    }

    private fun getTotalPriceByUserOrder(
        userOrderRequestDTO: UserOrderRequestDTO,
        listProducts: List<Product>
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
}