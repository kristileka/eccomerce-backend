package lunets.co.henez.controllers

import lunets.co.henez.jpa.entities.Category
import lunets.co.henez.jpa.repositories.CategoryRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/v2/category")
class CategoryController(
    private val categoryRepository: CategoryRepository,
) {
    @GetMapping
    fun getCategories(): ResponseEntity<List<Category>> {
        return ResponseEntity.ok(categoryRepository.findAll())
    }

    @CrossOrigin("*")
    @PostMapping
    fun createCategory(@Valid @RequestBody category: Category): ResponseEntity<Category> {
        return ResponseEntity.ok(categoryRepository.save(category))
    }

    @PatchMapping
    fun updateCategory(@RequestBody category: Category): ResponseEntity<Category> {
        return ResponseEntity.ok(categoryRepository.save(category))
    }

}