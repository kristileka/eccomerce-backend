package lunets.co.henez.controllers

import lunets.co.henez.jpa.entities.Category
import lunets.co.henez.jpa.repositories.CategoryRepository
import lunets.co.henez.services.CategoryService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/v2/category")
class CategoryController(
    private val categoryRepository: CategoryRepository,
    private val categoryService: CategoryService
) {
    @GetMapping
    fun getCategories(): ResponseEntity<List<Category>> {
        return ResponseEntity.ok(categoryRepository.findAll())
    }

    @PostMapping
    fun createCategory(@Valid @RequestBody category: Category): ResponseEntity<Category> {
        return ResponseEntity.ok(categoryRepository.save(category))
    }

    @PatchMapping("/{id}")
    fun updateCategory(
        @PathVariable("id") id: String,
        @RequestBody category: Category
    ): ResponseEntity<Category> {
        return ResponseEntity.ok(categoryService.overrideCategory(id, category))
    }


}