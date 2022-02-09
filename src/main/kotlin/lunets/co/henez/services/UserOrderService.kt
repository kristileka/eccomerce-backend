package lunets.co.henez.services

import lunets.co.henez.dto.UserOrderRequestDTO
import lunets.co.henez.jpa.entities.UserOrder

interface UserOrderService {
    fun createOrder(userOrderRequestDTO: UserOrderRequestDTO): UserOrder
}