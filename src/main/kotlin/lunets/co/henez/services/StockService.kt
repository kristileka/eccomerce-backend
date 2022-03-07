package lunets.co.henez.services

import lunets.co.henez.jpa.entities.AvailableStock
import lunets.co.henez.jpa.entities.Category
import lunets.co.henez.jpa.entities.Product

interface StockService {
    fun overrideStock(id: String, stock: AvailableStock): AvailableStock
}