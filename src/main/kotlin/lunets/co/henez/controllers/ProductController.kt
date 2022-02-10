package lunets.co.henez.controllers

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

    @CrossOrigin("*")
    @PostMapping
    fun createProduct(@Valid @RequestBody product: Product): ResponseEntity<Product> {
        return ResponseEntity.ok(productRepository.save(product))
    }
    @CrossOrigin("*")
    @PatchMapping
    fun updateProduct(@RequestBody product: Product): ResponseEntity<Product> {
        return ResponseEntity.ok(productRepository.save(product))
    }

}