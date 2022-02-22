package lunets.co.henez.controllers

import lunets.co.henez.jpa.entities.AvailableStock
import lunets.co.henez.jpa.entities.Product
import lunets.co.henez.jpa.repositories.AvailableStockRepository
import lunets.co.henez.jpa.repositories.ProductRepository
import lunets.co.henez.services.ProductService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/v2/product")
class ProductController(
    private val productRepository: ProductRepository,
    private val productService: ProductService,
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
        return ResponseEntity.ok(productService.overrideProduct(id, product))
    }

    @PatchMapping("/{id}/add/stock")
    fun addStock(
        @PathVariable("id") id: String,
        @RequestBody listAvailableStock: List<AvailableStock>
    ): ResponseEntity<Product> {
        return ResponseEntity.ok(productService.addStock(id, listAvailableStock))
    }

    @GetMapping("/category/{categoryId}")
    fun getProductsByCategory(@PathVariable categoryId: String): ResponseEntity<List<Product>> {
        return ResponseEntity.ok(productService.filterProductsByCategory(categoryId))
    }
}