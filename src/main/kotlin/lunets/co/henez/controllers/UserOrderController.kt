package lunets.co.henez.controllers

import lunets.co.henez.dto.UserOrderRequestDTO
import lunets.co.henez.jpa.entities.Product
import lunets.co.henez.jpa.entities.UserOrder
import lunets.co.henez.jpa.repositories.UserOrderRepository
import lunets.co.henez.services.UserOrderService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/v2/userOrder")
class UserOrderController(
    private val userOrderService: UserOrderService
) {

    @PostMapping
    fun createOrder(@Valid @RequestBody userOrder: UserOrderRequestDTO): ResponseEntity<UserOrder> {
        return ResponseEntity.ok(userOrderService.createOrder(userOrder))
    }
}