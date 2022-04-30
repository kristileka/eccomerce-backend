package lunets.co.henez.jpa.repositories

import lunets.co.henez.jpa.entities.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import java.util.*


interface CategoryRepository : JpaRepository<Category, Long>, JpaSpecificationExecutor<Category> {
    fun findByIdIn(ids: List<Long>): List<Category>
    override fun findAll(): List<Category>
}

interface ProductRepository : JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    override fun findAll(): List<Product>
    fun findByIdIn(ids: List<Long>): List<Product>
    override fun findById(id: Long): Optional<Product>
}


interface AvailableStockRepository : JpaRepository<AvailableStock, Long>, JpaSpecificationExecutor<AvailableStock> {
    override fun findAll(): List<AvailableStock>
    fun findByIdIn(ids: List<Long>): List<AvailableStock>

}

interface UserOrderRepository : JpaRepository<UserOrder, Long>, JpaSpecificationExecutor<UserOrder> {
    override fun findAll(): List<UserOrder>
}
