package lunets.co.henez.services.impl

import lunets.co.henez.jpa.entities.AvailableStock
import lunets.co.henez.jpa.entities.Category
import lunets.co.henez.jpa.entities.Product
import lunets.co.henez.jpa.repositories.AvailableStockRepository
import lunets.co.henez.jpa.repositories.CategoryRepository
import lunets.co.henez.jpa.repositories.ProductRepository
import lunets.co.henez.services.CategoryService
import lunets.co.henez.services.ProductService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class CategoryServiceImpl(
    private val categoryRepository: CategoryRepository,
) : CategoryService {
    override fun overrideCategory(id: String, category: Category): Category {
        return categoryRepository.save(category.apply {
            this.id = id.toLong()
        })
    }

}