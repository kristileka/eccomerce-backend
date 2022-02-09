package lunets.co.henez.services.impl

import lunets.co.henez.dto.UserOrderRequestDTO
import lunets.co.henez.jpa.entities.OrderItem
import lunets.co.henez.jpa.entities.UserOrder
import lunets.co.henez.jpa.repositories.OrderItemRepository
import lunets.co.henez.jpa.repositories.ProductRepository
import lunets.co.henez.jpa.repositories.UserOrderRepository
import lunets.co.henez.services.UserOrderService
import org.springframework.stereotype.Service

@Service
class UserOrderServiceImpl(
    private val userOrderRepository: UserOrderRepository,
    private val productRepository: ProductRepository,
    private val orderItemRepository: OrderItemRepository
) : UserOrderService {
    override fun createOrder(userOrderRequestDTO: UserOrderRequestDTO): UserOrder {
        val products = productRepository.findByIdIn(userOrderRequestDTO.orderItems.map {
            it.productId
        })
        val userOrder = userOrderRepository.save(UserOrder().apply {
            this.name = userOrderRequestDTO.name
            this.address = userOrderRequestDTO.address
            this.phoneNumber = userOrderRequestDTO.phoneNumber
            this.email = userOrderRequestDTO.email
        })

        userOrder.orderItems = userOrderRequestDTO.orderItems.map { orderItem ->
            orderItemRepository.save(OrderItem().apply {
                this.quantity = orderItem.quantity
                this.product = products.first { it.id == orderItem.productId }
                this.userOrder = userOrder
            })
        }
        return userOrderRepository.save(userOrder)
    }
}