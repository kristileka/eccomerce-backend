package lunets.co.henez.controllers


import lunets.co.henez.jpa.entities.Category
import lunets.co.henez.services.CategoryService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v2/stock")
class StockController(
    private val categoryService: CategoryService
) {
    @PatchMapping("/{id}")
    fun updateStock(
        @PathVariable("id") id: String,
        @RequestBody category: Category
    ): ResponseEntity<Category> {
        return ResponseEntity.ok(categoryService.overrideCategory(id, category))
    }
}