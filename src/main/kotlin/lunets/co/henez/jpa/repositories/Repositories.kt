package lunets.co.henez.jpa.repositories

import lunets.co.henez.jpa.entities.Category
import lunets.co.henez.jpa.entities.OrderItem
import lunets.co.henez.jpa.entities.UserOrder
import lunets.co.henez.jpa.entities.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor


interface CategoryRepository : JpaRepository<Category, Long>, JpaSpecificationExecutor<Category> {
    override fun findAll(): List<Category>
}

interface ProductRepository : JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    override fun findAll(): List<Product>
    fun findByIdIn(ids: List<Long>): List<Product>
}

interface OrderItemRepository : JpaRepository<OrderItem, Long>, JpaSpecificationExecutor<OrderItem> {
    override fun findAll(): List<OrderItem>
}

interface UserOrderRepository : JpaRepository<UserOrder, Long>, JpaSpecificationExecutor<UserOrder> {
    override fun findAll(): List<UserOrder>
}
