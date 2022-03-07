package lunets.co.henez.services

import lunets.co.henez.jpa.entities.Category

interface CategoryService {
    fun overrideCategory(id: String, category: Category): Category
}