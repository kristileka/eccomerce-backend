package lunets.co.henez.controllers

import lunets.co.henez.jpa.entities.AvailableStock
import lunets.co.henez.jpa.entities.Product
import lunets.co.henez.jpa.repositories.ProductRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/v2/product")
class ProductController(
    private val productRepository: ProductRepository
) {
    @GetMapping
    fun getProducts(): ResponseEntity<List<Product>> {
        return ResponseEntity.ok(productRepository.findAll())
    }

    @PostMapping
    fun createProduct(@Valid @RequestBody product: Product): ResponseEntity<Product> {
        return ResponseEntity.ok(productRepository.save(product))
    }

    @PatchMapping("/{id}")
    fun updateProduct(
        @PathVariable("id") id: String,
        @RequestBody product: Product
    ): ResponseEntity<Product> {
        return ResponseEntity.ok(productRepository.save(product.apply {
            this.id = id.toLong()
        }))
    }

    @PatchMapping("/stock_by_product/{id}")
    fun addStock(
        @PathVariable("id") id: String,
        @RequestBody listAvailableStock: List<AvailableStock>
    ): ResponseEntity<Product> {
        val product = productRepository.findById(id.toLong())
        var availableStocks = mutableListOf<AvailableStock>()
        availableStocks.addAll(listAvailableStock)
        if (product.isPresent) {
            availableStocks.addAll(product.get().availableStocks)
            return ResponseEntity.ok(productRepository.save(product.get().apply {
                this.availableStocks = availableStocks
            }))
        } else {
            throw java.lang.RuntimeException("Product not found!")
        }
    }

    @GetMapping("/category/{categoryId}")
    fun getProductsByCategory(@PathVariable categoryId: String): ResponseEntity<List<Product>> {
        return ResponseEntity.ok(productRepository.findAll().filter { it ->
            it.categories.any {
                it.id == categoryId.toLong()
            }
        })
    }

}