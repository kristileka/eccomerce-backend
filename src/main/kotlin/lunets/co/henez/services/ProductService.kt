package lunets.co.henez.services

import lunets.co.henez.jpa.entities.AvailableStock
import lunets.co.henez.jpa.entities.Product

interface ProductService {
    fun addStock(id: String, listAvailableStock: List<AvailableStock>): Product
    fun overrideProduct(id: String, product: Product): Product
    fun filterProductsByCategory(categoryId: String): List<Product>
    fun removeStocks(id: String, listAvailableStock: List<Int>): Product?
}